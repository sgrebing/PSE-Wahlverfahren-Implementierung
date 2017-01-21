/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pse.beast.booleanexpeditor;

import edu.pse.beast.stringresource.StringLoaderInterface;

import javax.swing.*;

/**
 *
 * @author scooby
 */
public class BooleanExpEditorWindow extends javax.swing.JFrame {

    /**
     * Creates new form BooleanExpEditorWindow
     */
    public BooleanExpEditorWindow(StringLoaderInterface stringLoaderInterface) {
        initComponents(stringLoaderInterface);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(StringLoaderInterface stringLoaderInterface) {
        //TODO load strings from stringloaderinterface
        toolbar = new javax.swing.JToolBar();
        symVarLabel = new javax.swing.JLabel();
        addSymVarButton = new javax.swing.JButton();
        removeSymVarButton = new javax.swing.JButton();
        prePropLabel = new javax.swing.JLabel();
        prePropScrollPane = new javax.swing.JScrollPane();
        prePropTextPane = new javax.swing.JTextPane();
        symVarScrollPane = new javax.swing.JScrollPane();
        symVarList = new javax.swing.JList<>();
        postPropLabel = new javax.swing.JLabel();
        postPropScrollPane = new javax.swing.JScrollPane();
        postPropTextPane = new javax.swing.JTextPane();
        errorScrollPane = new javax.swing.JScrollPane();
        errorTextPane = new javax.swing.JTextPane();
        menubar = new javax.swing.JMenuBar();
        prePropTextLineNumber = new TextLineNumber(prePropTextPane);
        postPropTextLineNumber = new TextLineNumber(postPropTextPane);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toolbar.setRollover(true);
        toolbar.setFloatable(false);

        prePropScrollPane.setViewportView(prePropTextPane);
        prePropScrollPane.setRowHeaderView(prePropTextLineNumber);
        symVarScrollPane.setViewportView(symVarList);

        postPropScrollPane.setViewportView(postPropTextPane);
        postPropScrollPane.setRowHeaderView(postPropTextLineNumber);

        errorScrollPane.setViewportView(errorTextPane);

        errorTextPane.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(symVarLabel)
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
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(symVarLabel)
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
    
    protected javax.swing.JTextPane getPrePropTextPane() {
        return prePropTextPane;
    }
    protected javax.swing.JTextPane getPostPropTextPane() {
        return postPropTextPane;
    }
    protected javax.swing.JTextPane getErrorTextPane() {
        return errorTextPane;
    }
    protected javax.swing.JList getSymVarList(){
        return symVarList;
    }
    protected javax.swing.JButton getAddSymVarButton(){
        return addSymVarButton;
    }
    protected javax.swing.JButton getRemoveSymVarButton(){
        return removeSymVarButton;
    }

    protected void setToolbar(javax.swing.JToolBar toolbar) {
        this.toolbar = toolbar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSymVarButton;
    private javax.swing.JButton removeSymVarButton;
    private javax.swing.JLabel symVarLabel;
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
    private TextLineNumber prePropTextLineNumber;
    private TextLineNumber postPropTextLineNumber;
    // End of variables declaration//GEN-END:variables
}