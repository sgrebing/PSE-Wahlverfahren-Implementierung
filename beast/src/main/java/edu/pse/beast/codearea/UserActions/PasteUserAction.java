/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pse.beast.codearea.UserActions;

import edu.pse.beast.codearea.CodeArea;
import edu.pse.beast.toolbox.UserAction;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this useraction asks the given codearea to insert the currently copied string
 * at the current position
 * @author Holger-Desktop
 */
public class PasteUserAction extends UserAction {
    private CodeArea codeArea;
    private Clipboard clipboard;

    public PasteUserAction(CodeArea area) {
        super("paste");
        this.codeArea = area;
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    @Override
    public void perform() {
        try {
            codeArea.getInsertToCode().getSaveBeforeRemove().save();
        } catch (BadLocationException ex) {
            Logger.getLogger(CutUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }  
        try {
            String clipboardString = (String) clipboard.getData(DataFlavor.stringFlavor);
            codeArea.insertString(clipboardString);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
