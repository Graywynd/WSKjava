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
public class AffecterCours implements AgentAction {
    
    private int id_cours;
    private int id_etudiant;

    

    /**
     * @return the id_etudiant
     */
    public int getId_etudiant() {
        return id_etudiant;
    }

    /**
     * @param id_etudiant the id_etudiant to set
     */
    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    /**
     * @return the id_cours
     */
    public int getId_cours() {
        return id_cours;
    }

    /**
     * @param id_cours the id_cours to set
     */
    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }
    
    
    
}
