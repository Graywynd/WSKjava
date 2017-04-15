/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

/**
 *
 * @author saif
 */
public interface Vocabulary {
    
    public static final int COURS_INTROUVABLE = 8;
    
    public static final String MSG_COURS_INTOUVABLE = "Cours Introuvable !!!";
    public static final String SERVER_AGENT = "Server Agent";
     
    
    
    
   public static final String COURS = "Cours";
   public static final String COURS_ID = "Id_cours";
   public static final String COURS_INTITULE = "intitule";
   public static final String COURS_DUREE = "duree";
   public static final String COURS_ID_ENSG = "id_ensgn";
   
   public static final String TEST = "Test";
   public static final String TEST_ID = "id";
   public static final String TEST_NOM = "nom";
   public static final String TEST_DUREE = "duree";
   public static final String TEST_ID_COURS = "id_cours";
   
   public static final String CREATE_COURS = "Create_cours";
   public static final String CREATE_COURS_INTITULE = "intitule";
   public static final String CREATE_COURS_DUREE = "duree";
   
   public static final String AFFECTER_COURS = "Affecter_cours";
   public static final String AFFECTER_COURS_ID_COURS = "id_cours";
   public static final String AFFECTER_COURS_ID_ETUDIANT = "id_etudiant";
  
   public static final String CREATE_TEST = "Create_test";
   public static final String CREATE_TEST_NOM = "nom";
   public static final String CREATE_TEST_DUREE = "duree";
   
   public static final String PASSER_TEST = "Passer_test";
   public static final String PASSER_TEST_ID_TEST = "id_test";
   public static final String PASSER_TEST_ID_ETUDIANT = "id_etudiant";
   
   public static final String PROBLEM = "Problem";
   public static final String PROBLEM_NUM = "num";
   public static final String PROBLEM_MSG="msg";
   
   public static final String INFORMATION_COURS = "Information_cours";
   public static final String INFORMATION_COURS_TYPE = "type";
   public static final String INFORMATION_COURS_ID_COURS="Id_cours";
}
