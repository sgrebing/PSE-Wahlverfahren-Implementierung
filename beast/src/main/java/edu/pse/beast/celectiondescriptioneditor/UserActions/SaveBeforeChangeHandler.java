/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pse.beast.celectiondescriptioneditor.UserActions;

import javax.swing.*;

/**
 * Class for checking whether the loaded CElectionDescription object has been modified since it was loaded.
 * @author NikolaiLMS
 */
public class SaveBeforeChangeHandler {
    private String preString = "";
    private JTextPane codeArea;

    /**
     * Constructor
     * @param codeArea JTextPane of the CElectionEditorCodeArea
     */
    public SaveBeforeChangeHandler(JTextPane codeArea) {
        this.codeArea = codeArea;
        updatePreValue();
    }

    public void addNewTextPane(JTextPane codeArea) {
        this.codeArea = codeArea;
        updatePreValue();
    }

    /**
     * Updates the "pre" variables used for comparison.
     * Called after a new PostAndPrePropertiesDescription object is loaded or saved.
     */
    void updatePreValue() {
        preString = codeArea.getText();
    }

    /**
     * @return true if the currently loaded CElectionDescription object differs from what is currently displayed
     * in CElectionEditorWindow, false otherwise
     */
    public boolean hasChanged() {
        return !(preString.equals((String) codeArea.getText()));
    }
    
}
