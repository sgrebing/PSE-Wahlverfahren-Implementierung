/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pse.beast.booleanexpeditor;

import edu.pse.beast.highlevel.DisplaysStringsToUser;
import edu.pse.beast.stringresource.StringLoaderInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

/**
 * The JFrame that serves as the View for the booleanexpeditor Package.
 * @author Nikolai
 */
public class BooleanExpEditorWindow extends javax.swing.JFrame implements DisplaysStringsToUser{

    /**
     * Constructor
     */
    BooleanExpEditorWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents( ) {
        toolbar = new javax.swing.JToolBar();
        symbVarLabel = new javax.swing.JLabel();
        addSymVarButton = new javax.swing.JButton();
        removeSymVarButton = new javax.swing.JButton();
        prePropLabel = new javax.swing.JLabel();
        prePropScrollPane = new javax.swing.JScrollPane();
        prePropTextPane = new javax.swing.JTextPane();
        symVarScrollPane = new javax.swing.JScrollPane();
        listModel = new DefaultListModel();
        symVarList = new javax.swing.JList<>(listModel);
        symVarList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        symVarList.setLayoutOrientation(JList.VERTICAL);
        postPropLabel = new javax.swing.JLabel();
        postPropScrollPane = new javax.swing.JScrollPane();
        postPropTextPane = new javax.swing.JTextPane();
        errorScrollPane = new javax.swing.JScrollPane();
        errorTextPane = new javax.swing.JTextPane();
        menubar = new javax.swing.JMenuBar();
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        toolbar.setRollover(true);
        toolbar.setFloatable(false);

        symVarScrollPane.setViewportView(symVarList);

        prePropScrollPane.setViewportView(prePropTextPane);

        postPropScrollPane.setViewportView(postPropTextPane);

        errorScrollPane.setViewportView(errorTextPane);

        errorTextPane.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(symbVarLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addSymVarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeSymVarButton))
                            .addComponent(prePropLabel))
                        .addGap(147, 193, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorScrollPane)
                            .addComponent(postPropScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(prePropScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(symVarScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(postPropLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(symbVarLabel)
                    .addComponent(addSymVarButton)
                    .addComponent(removeSymVarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(symVarScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prePropLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prePropScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postPropLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postPropScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Update the language dependent displayed Strings in this class.
     * @param stringLoaderInterface the new stringLoaderInterface
     */
    public void updateStringRes(StringLoaderInterface stringLoaderInterface) {
        postPropLabel.setText(stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("postProperties"));
        prePropLabel.setText(stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("preProperties"));
        addSymVarButton.setText(stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("add"));
        removeSymVarButton.setText(stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("remove"));
        symbVarLabel.setText(stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("symbolicVariablesLabel"));
        titleString = stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("windowTitle");
        setTitle(titleString);
        saveChanges = stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("saveChanges");
        save = stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("save");
        cancelOption = stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("cancelOption");
        noOption = stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("noOption");
        yesOption = stringLoaderInterface.getBooleanExpEditorStringResProvider().
                getBooleanExpEditorWindowStringRes().getStringFromID("yesOption");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSymVarButton;
    private javax.swing.JButton removeSymVarButton;
    private javax.swing.JLabel symbVarLabel;
    private javax.swing.JLabel prePropLabel;
    private javax.swing.JLabel postPropLabel;
    private javax.swing.JList<String> symVarList;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JScrollPane prePropScrollPane;
    private javax.swing.JScrollPane symVarScrollPane;
    private javax.swing.JScrollPane postPropScrollPane;
    private javax.swing.JScrollPane errorScrollPane;
    private javax.swing.JTextPane postPropTextPane;
    private javax.swing.JTextPane errorTextPane;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JTextPane prePropTextPane;
    private JTextPane focusedTextPane;
    private DefaultListModel listModel;
    private String titleString;
    private String saveChanges;
    private String save;
    private String yesOption;
    private String noOption;
    private String cancelOption;
    // End of variables declaration//GEN-END:variable

    /**
     * Getter
     * @return The JTextPane for the precondition.
     */
    javax.swing.JTextPane getPrePropTextPane() {
        return prePropTextPane;
    }

    /**
     * Getter
     * @return The JScrollPane for the precondition.
     */
    javax.swing.JScrollPane getPrePropScrollPane() {
        return prePropScrollPane;
    }

    /**
     * Getter
     * @return The JTextPane for the postcondition.
     */
    javax.swing.JTextPane getPostPropTextPane() {
        return postPropTextPane;
    }

    /**
     * Getter
     * @return The JScrollPane for the postcondition.
     */
    javax.swing.JScrollPane getPostPropScrollPane() {
        return postPropScrollPane;
    }

    /**
     * Getter
     * @return the JTextPane for static error displaying
     */
    javax.swing.JTextPane getErrorTextPane() {
        return errorTextPane;
    }

    /**
     * Getter
     * @return the JList that contains the user defined symbolic variables
     */
    javax.swing.JList getSymVarList(){
        return symVarList;
    }

    /**
     * Getter
     * @return the JButton to add symbolic variables
     */
    javax.swing.JButton getAddSymVarButton(){
        return addSymVarButton;
    }

    /**
     * Getter
     * @return the JButton to remove symbolic variables
     */
    javax.swing.JButton getRemoveSymVarButton(){
        return removeSymVarButton;
    }

    /**
     * Adds the given string to the window title, used for displaying name of currently loaded PostAndPrePropDescription
     * @param s
     */
    void setWindowTitle(String s) {
        this.setTitle(titleString + " " + s);
    }

    /**
     * Getter
     * @return toolbar the JToolBar object.
     */
    JToolBar getToolbar() {
        return toolbar;
    }

    /**
     * Method that opens pane that asks the user whether he wants to save or not.
     * @return the option clicked by the user
     */
    public int showOptionPane(String propertyName) {
        Object[] options = {yesOption,
                noOption,
                cancelOption};
        return  JOptionPane.showOptionDialog(this,
                saveChanges + propertyName + save,
                "",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public void setNewTextpanes() {
        prePropTextPane = new JTextPane();
        prePropScrollPane.setViewportView(prePropTextPane);
        postPropTextPane = new JTextPane();
        postPropScrollPane.setViewportView(postPropTextPane);
    }

}
