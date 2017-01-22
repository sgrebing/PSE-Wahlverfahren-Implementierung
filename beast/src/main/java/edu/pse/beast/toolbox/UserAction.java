/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pse.beast.toolbox;

/**
 *
 * @author Holger-Desktop
 */
public abstract class UserAction {
    private String id;
    
    /**
     * 
     * @param id the identification
     */
    public UserAction(String id) {
        this.id = id;
    }
    
    /**
     * 
     * @return the identification
     */
    public String getId() {
        return id;
    }
    
    /**
     * performs the action of the user
     */
    public abstract void perform();
}