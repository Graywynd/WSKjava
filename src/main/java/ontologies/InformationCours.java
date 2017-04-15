/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologies;

import jade.content.*;

/**
 *
 * @author saif
 */
public class InformationCours implements AgentAction {
    
    private int type;
    private int Id_Cours;

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the Id_Cours
     */
    public int getId_Cours() {
        return Id_Cours;
    }

    /**
     * @param Id_Cours the Id_Cours to set
     */
    public void setId_Cours(int Id_Cours) {
        this.Id_Cours = Id_Cours;
    }
    
    
    
}
