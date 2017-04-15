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
public class Test implements Concept {
    
    private int id;
    private int id_cours;
    private String nom_test;
    private int duree;

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

    /**
     * @return the nom_test
     */
    public String getNom_test() {
        return nom_test;
    }

    /**
     * @param nom_test the nom_test to set
     */
    public void setNom_test(String nom_test) {
        this.nom_test = nom_test;
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
    
}
