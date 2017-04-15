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
public class Cours implements Concept {
    
    private int id_cours;
    private String intitule;
    private int duree;
    private int id_ensgn;

    /**
     * @return the intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * @param intitule the intitule to set
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * @return the duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * @param duree the duree to set
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * @return the id_ensgn
     */
    public int getId_ensgn() {
        return id_ensgn;
    }

    /**
     * @param id_ensgn the id_ensgn to set
     */
    public void setId_ensgn(int id_ensgn) {
        this.id_ensgn = id_ensgn;
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
