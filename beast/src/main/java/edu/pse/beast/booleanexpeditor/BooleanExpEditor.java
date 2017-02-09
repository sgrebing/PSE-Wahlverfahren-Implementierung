/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pse.beast.booleanexpeditor;

import edu.pse.beast.booleanexpeditor.UserActions.SaveBeforeChangeHandler;
import edu.pse.beast.booleanexpeditor.View.BooleanExpEditorWindow;
import edu.pse.beast.booleanexpeditor.View.BooleanExpEditorWindowStarter;
import edu.pse.beast.booleanexpeditor.View.ErrorWindow;
import edu.pse.beast.booleanexpeditor.booleanExpCodeArea.BooleanExpCodeArea;
import edu.pse.beast.booleanexpeditor.booleanExpCodeArea.BooleanExpCodeAreaBuilder;
import edu.pse.beast.booleanexpeditor.booleanExpCodeArea.CodeAreaFocusListener;
import edu.pse.beast.booleanexpeditor.booleanExpCodeArea.errorFinder.BooleanExpErrorDisplayer;
import edu.pse.beast.celectiondescriptioneditor.CElectionDescriptionEditor;
import edu.pse.beast.codearea.ErrorHandling.CodeError;
import edu.pse.beast.datatypes.propertydescription.FormalPropertiesDescription;
import edu.pse.beast.datatypes.propertydescription.PostAndPrePropertiesDescription;
import edu.pse.beast.stringresource.StringLoaderInterface;
import edu.pse.beast.saverloader.FileChooser;
import edu.pse.beast.toolbox.ObjectRefsForBuilder;
import edu.pse.beast.toolbox.UserAction;

import java.util.ArrayList;

/**
 * The main class of this package that serves as an interface to the outside.
 * Part of the "Controller" in an MVC-Pattern.
 * @author NikolaiLMS
 */
public class BooleanExpEditor {
    private final BooleanExpEditorWindow window;
    private final SymbolicVarListController symbolicVarListController;
    private final BooleanExpEditorWindowStarter windowStarter;
    private final ErrorWindow errorWindow;
    private PostAndPrePropertiesDescription currentlyLoadedPostAndPreProp;
    private BooleanExpEditorMenubarHandler menuBarHandler;
    private BooleanExpEditorToolbarHandler toolBarHandler;
    private BooleanExpCodeArea prePropCodeArea;
    private BooleanExpCodeArea postPropCodeArea;
    private final SaveBeforeChangeHandler saveBeforeChangeHandler;
    private final CodeAreaFocusListener codeAreaFocusListener;
    private final BooleanExpCodeAreaBuilder codeAreaBuilder;
    private ObjectRefsForBuilder refs;
    private CElectionDescriptionEditor cEditor;
    private boolean loadedFromPropertyList;
    private final FileChooser fileChooser;
    private StringLoaderInterface stringLoaderInterface;
    private ArrayList<UserAction> userActions = new ArrayList<>();
    private ArrayList<Character> userActionChars = new ArrayList<>();
    /**
     * Temporary Constructor declaration to build BooleanExpEditor for Dummy-View
     * @param window BooleanExpEditorWindow object
     * @param symbolicVarListController SymbolicVarListController object
     */
    BooleanExpEditor(BooleanExpCodeArea prePropCodeArea,
                     BooleanExpCodeArea postPropCodeArea,
                     BooleanExpEditorWindow window,
                     SymbolicVarListController symbolicVarListController,
                     ErrorWindow errorWindow,
                     SaveBeforeChangeHandler saveBeforeChangeHandler,
                     CodeAreaFocusListener codeAreaFocusListener,
                     PostAndPrePropertiesDescription postAndPrePropertiesDescription,
                     BooleanExpCodeAreaBuilder codeAreaBuilder,
                     ObjectRefsForBuilder refs,
                     CElectionDescriptionEditor cEditor,
                     FileChooser fileChooser) {
        this.window = window;
        this.refs = refs;
        this.errorWindow = errorWindow;
        this.currentlyLoadedPostAndPreProp = postAndPrePropertiesDescription;
        this.symbolicVarListController = symbolicVarListController;
        this.prePropCodeArea = prePropCodeArea;
        this.postPropCodeArea = postPropCodeArea;
        this.saveBeforeChangeHandler = saveBeforeChangeHandler;
        this.codeAreaBuilder = codeAreaBuilder;
        this.cEditor = cEditor;
        this.fileChooser = fileChooser;
        this.stringLoaderInterface = refs.getStringIF();
        prePropCodeArea.getPane().addFocusListener(codeAreaFocusListener);
        postPropCodeArea.getPane().addFocusListener(codeAreaFocusListener);
        this.codeAreaFocusListener = codeAreaFocusListener;
        letUserEditPostAndPreProperties(postAndPrePropertiesDescription, false);
        windowStarter = new BooleanExpEditorWindowStarter(window);
    }

    void setToolBarHandler(BooleanExpEditorToolbarHandler toolBarHandler) {
        this.toolBarHandler = toolBarHandler;
    }

    void setMenuBarHandler(BooleanExpEditorMenubarHandler menuBarHandler) {
        this.menuBarHandler = menuBarHandler;
    }

    public PostAndPrePropertiesDescription getCurrentlyLoadedPostAndPreProp() {
        updatePostAndPrePropObject();
        return currentlyLoadedPostAndPreProp;
    }

    /**
     * Executes the showWindow function in windowStarter.
     */
    public void showWindow() {
        windowStarter.showWindow();
    }

    public boolean findErrorsAndDisplayThem() {
        ArrayList<CodeError> prePropErrors = prePropCodeArea.getErrorCtrl().getErrorFinderList().getErrors();
        ArrayList<CodeError> postPropErrors = postPropCodeArea.getErrorCtrl().getErrorFinderList().getErrors();
        errorWindow.displayErrors(prePropErrors, postPropErrors,
                ((BooleanExpErrorDisplayer) prePropCodeArea.getErrorCtrl().getDisplayer()));
        updatePostAndPrePropObject();
        if (prePropErrors.size() == 0 && postPropErrors.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Loads and displays the given PostAndPrePropertiesDescrion Object.
     * @param postAndPrePropertiesDescription The PostAndPrePropertiesDescription Object
     * @param loadedFromPropertyList boolean that is used to know whether the object is loaded from the propertylist,
     *                               or from inside the editor, used for SaveBeforeChange handling
     * @return a boolean stating the success of the loading
     */
    public boolean letUserEditPostAndPreProperties(PostAndPrePropertiesDescription postAndPrePropertiesDescription,
                                                   boolean loadedFromPropertyList) {
        if (!this.loadedFromPropertyList) {
            if (saveBeforeChangeHandler.ifHasChangedOpenSaveDialog(currentlyLoadedPostAndPreProp.getName())) {
                loadNewProperties(postAndPrePropertiesDescription);
                this.loadedFromPropertyList = loadedFromPropertyList;
                return true;
            } else {
                return false;
            }
        } else {
            loadNewProperties(postAndPrePropertiesDescription);
            this.loadedFromPropertyList = loadedFromPropertyList;
            return true;
        }
    }

    public void loadNewProperties(PostAndPrePropertiesDescription postAndPrePropertiesDescription) {
        updatePostAndPrePropObject();
        System.out.println("Loading symbolic variable list");
        currentlyLoadedPostAndPreProp = postAndPrePropertiesDescription;
        symbolicVarListController.setSymbVarList(postAndPrePropertiesDescription.getSymbolicVariableList());
        window.setNewTextpanes();
        saveBeforeChangeHandler.addNewTextPanes(window.getPrePropTextPane(), window.getPostPropTextPane());
        
        cEditor.removeListener(prePropCodeArea.getVariableErrorFinder());
        cEditor.removeListener(postPropCodeArea.getVariableErrorFinder());
        
        symbolicVarListController.getSymbolicVariableList().removeListener(prePropCodeArea.getVariableErrorFinder().getLis());
        symbolicVarListController.getSymbolicVariableList().removeListener(postPropCodeArea.getVariableErrorFinder().getLis());
        
        prePropCodeArea = codeAreaBuilder.createBooleanExpCodeAreaObject(refs, window.getPrePropTextPane(),
                window.getPrePropScrollPane(), symbolicVarListController.getSymbolicVariableList(), cEditor);
        postPropCodeArea = codeAreaBuilder.createBooleanExpCodeAreaObject(refs, window.getPostPropTextPane(),
                window.getPostPropScrollPane(), symbolicVarListController.getSymbolicVariableList(), cEditor);     
        
        for (int i = 0; i < userActions.size(); i++) {
            UserAction get = userActions.get(i);
            char c = userActionChars.get(i);
            prePropCodeArea.linkActionToShortcut(c, get);
            postPropCodeArea.linkActionToShortcut(c, get);
        }
                
        cEditor.addListener(prePropCodeArea.getVariableErrorFinder());
        cEditor.addListener(postPropCodeArea.getVariableErrorFinder());
        
        postPropCodeArea.getVariableErrorFinder().inputChanged(cEditor.getElectionDescription().getInputType());
        postPropCodeArea.getVariableErrorFinder().outputChanged(cEditor.getElectionDescription().getOutputType());
        
        prePropCodeArea.getVariableErrorFinder().inputChanged(cEditor.getElectionDescription().getInputType());
        prePropCodeArea.getVariableErrorFinder().outputChanged(cEditor.getElectionDescription().getOutputType());    
        
        prePropCodeArea.getPane().addFocusListener(codeAreaFocusListener);
        postPropCodeArea.getPane().addFocusListener(codeAreaFocusListener);
        codeAreaFocusListener.addNewCodeAreas(prePropCodeArea, postPropCodeArea);

        System.out.println("Loading properties");
        prePropCodeArea.getPane().setText(postAndPrePropertiesDescription.getPrePropertiesDescription().getCode());
        postPropCodeArea.getPane().setText(postAndPrePropertiesDescription.getPostPropertiesDescription().getCode());

        System.out.println("Finding errors");
        this.findErrorsAndDisplayThem();
        this.window.setWindowTitle(postAndPrePropertiesDescription.getName());
        saveBeforeChangeHandler.updatePreValues();
    }

    /**
     * Getter
     * @return the CodeAreaFocusListener instance of this class
     */
    public CodeAreaFocusListener getCodeAreaFocusListener() {
        return codeAreaFocusListener;
    }

    public boolean isCorrect() {
        return findErrorsAndDisplayThem();
    }

    public void updatePostAndPrePropObject() {
        currentlyLoadedPostAndPreProp.setPrePropertiesDescription(
                new FormalPropertiesDescription(prePropCodeArea.getPane().getText()));
        currentlyLoadedPostAndPreProp.setPostPropertiesDescription(
                new FormalPropertiesDescription(postPropCodeArea.getPane().getText()));
        currentlyLoadedPostAndPreProp.getSymVarList().setSymbolicVariableList(symbolicVarListController.getSymbolicVariableList().getSymbolicVariables());
    }


    /**
     * Getter
     * @return the BooleanExpEditorWindow instance of this class
     */
    public BooleanExpEditorWindow getView() {
        return window;
    }

    public BooleanExpCodeArea getPostPropCodeArea() {
        return postPropCodeArea;
    }

    public BooleanExpCodeArea getPrePropCodeArea() {
        return prePropCodeArea;
    }

    public SaveBeforeChangeHandler getSaveBeforeChangeHandler() {
        return saveBeforeChangeHandler;
    }

    public FileChooser getFileChooser(){
        return fileChooser;
    }

    public StringLoaderInterface getStringInterface() {
        return this.stringLoaderInterface;
    }

    public void updateStringIf(StringLoaderInterface stringLoaderInterface) {
        this.stringLoaderInterface = stringLoaderInterface;
        window.updateStringRes(stringLoaderInterface);
        symbolicVarListController.updateStringRes(stringLoaderInterface);
        menuBarHandler.updateStringRes(stringLoaderInterface);
        toolBarHandler.updateStringRes(stringLoaderInterface);
    }
    
    public void addUserAction(char c, UserAction ac) {
        userActions.add(ac);
        userActionChars.add(c);
    }
}