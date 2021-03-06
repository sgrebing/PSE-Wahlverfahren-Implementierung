package edu.pse.beast.options;

import edu.pse.beast.options.BooleanExpEditorOptions.BooleanExpEditorOptions;
import edu.pse.beast.options.ParametereditorOptions.ParametereditorOptions;
import edu.pse.beast.options.ParametereditorOptions.LanguageOptions;
import edu.pse.beast.options.CEditorOptions.CElectionEditorOptions;
import edu.pse.beast.booleanexpeditor.BooleanExpEditor;
import edu.pse.beast.celectiondescriptioneditor.CElectionDescriptionEditor;
import edu.pse.beast.highlevel.PSECentralObjectProvider;
import edu.pse.beast.parametereditor.ParameterEditor;
import edu.pse.beast.saverloader.OptionSaverLoader.OptionsSaverLoaderInterface;
import edu.pse.beast.stringresource.StringLoaderInterface;
import edu.pse.beast.toolbox.ObjectRefsForBuilder;
import java.io.IOException;

/**
 * Class providing access to different OptionElement subclasses and an OptionPresenter
 */
public class OptionsInterface {    
    private OptionPresenter presenter;
    private LanguageOptions languageOptions;
    private BooleanExpEditorOptions booleanExpEditorOptions;
    private CElectionEditorOptions cElectionEditorOptions;
    private ParametereditorOptions parametereditorOptions;

    /**
     * Getter for the BooleanExpEditorOptions
     * @param editor BooleanExpEditor
     * @param refs ObjectRefsForBuilder
     * @return BooleanExpEditorOptions
     */
    public BooleanExpEditorOptions getBooleanExpEditorOptions(BooleanExpEditor editor,
            ObjectRefsForBuilder refs) {
        if(booleanExpEditorOptions == null) {
            try {
            booleanExpEditorOptions = OptionsSaverLoaderInterface.loadBooleanExpEditorOpts(editor);
            } catch (IOException ex) {            
                ex.printStackTrace();
                booleanExpEditorOptions = new BooleanExpEditorOptions(editor);
            }            
        }        
        return booleanExpEditorOptions;
    }

    /**
     * Getter for the CElectionEditorOptions
     * @param editor the CElectionEditor
     * @return CElectionEditorOptions
     */
    public CElectionEditorOptions getCElectionEditorOptions(CElectionDescriptionEditor editor) {
        if(cElectionEditorOptions == null) {
            try {
                cElectionEditorOptions = OptionsSaverLoaderInterface.loadCEditorOpts(editor);
            } catch (Exception ex) {                
                cElectionEditorOptions = new CElectionEditorOptions(editor);
            }            
        }        
        return cElectionEditorOptions;
    }

    /**
     * Getter for the ParametereditorOptions
     * @param langOpts LanguageOptions
     * @param editor ParameterEditor
     * @param centralObjectProvider PSECentralObjectProvider
     * @return ParametereditorOptions
     */
    public ParametereditorOptions getParameterEditorOptions(
            LanguageOptions langOpts, ParameterEditor editor, PSECentralObjectProvider centralObjectProvider) {
        if(parametereditorOptions == null) {
            try {
                parametereditorOptions = OptionsSaverLoaderInterface.loadParameterEditorOpts(
                        langOpts,                
                        editor,
                        centralObjectProvider);
            } catch(Exception e) {
                parametereditorOptions = new ParametereditorOptions(
                    langOpts, editor, centralObjectProvider);
            }         
        }
        return parametereditorOptions;
    }

    /**
     * Getter for the LanguageOptions
     * @param stringIf StringLoaderInterface
     * @return LanguageOptions
     */
    public LanguageOptions getLanguageOptions(StringLoaderInterface stringIf) {
        if(languageOptions == null) {
            try {
                languageOptions = OptionsSaverLoaderInterface.loadLangOpts(stringIf);
            } catch (IOException ex) {
                languageOptions = new LanguageOptions(stringIf);
            }            
        }
        return languageOptions;
    }

    /**
     * Getter for the OptionPresenter
     * @param refs ObjectRefsForBuilder
     * @return OptionPresenter
     */
    public OptionPresenter getOptionPresenter(ObjectRefsForBuilder refs) {
        if(presenter == null) {
            presenter = new OptionPresenter(refs.getStringIF().getOptionStringResProvider().getOptionStringRes());
            refs.getLanguageOpts().addStringDisplayer(presenter);
        }
        return presenter;
    }
}
