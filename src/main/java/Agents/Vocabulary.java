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
     
    
    
    //cours
   public static final String COURS = "Cours";
   public static final String COURS_ID = "Id_cours";
   public static final String COURS_INTITULE = "intitule";
   public static final String COURS_DUREE = "duree";
   public static final String COURS_ENSEIGNANT = "enseignant";
   public static final String COURS_SEANCES = "seances";
   public static final String COURS_TESTS = "tests";
   public static final String COURS_COURSETUDIANT= "coursetudiant";

   //test
   public static final String TEST = "Test";
   public static final String TEST_ID = "id_test";
   public static final String TEST_NOM = "nom_test";
   public static final String TEST_DUREE = "duree_test";
   public static final String TEST_COURS = "cours_test";
   public static final String TEST_ETUDIANTS = "testetudiant";

   //create cours
   public static final String CREATE_COURS = "Create_cours";
   public static final String CREATE_COURS_INTITULE = "intitule";
   public static final String CREATE_COURS_DUREE = "duree";
   public static final String CREATE_COURS_ENSEIGNANT = "enseignant";

   //affecter cours
   public static final String AFFECTER_COURS = "Affecter_cours";
   public static final String AFFECTER_COURS_ID_COURS = "id_cours";
   public static final String AFFECTER_COURS_ID_ETUDIANT = "id_etudiant";

   //create test
   public static final String CREATE_TEST = "Create_test";
   public static final String CREATE_TEST_NOM = "nom";
   public static final String CREATE_TEST_DUREE = "duree";

   //passer test
   public static final String PASSER_TEST = "Passer_test";
   public static final String PASSER_TEST_ID_TEST = "id_test";
   public static final String PASSER_TEST_ID_ETUDIANT = "id_etudiant";

   //probleme
   public static final String PROBLEM = "Problem";
   public static final String PROBLEM_NUM = "num";
   public static final String PROBLEM_MSG="msg";

   //information cours
   public static final String INFORMATION_COURS = "Information_cours";
   public static final String INFORMATION_COURS_TYPE = "type";
   public static final String INFORMATION_COURS_ID_COURS="Id_cours";

   //list cours enseignant
   public static final String LIST_COURS_ENSEIGNANT = "List_cours_enseignant";
   public static final String LIST_COURS_ENSEIGNANT_ID = "";


   //etudiant
   public static final String ETUDIANT="Etudiant";
   public static final String ETUDIANT_ID="id_etudiant";
   public static final String ETUDIANT_NOM="nom_etudiant";
   public static final String ETUDIANT_COURS_ETUDIANT="coursetudiant";
   public static final String ETUDIANT_TEST_ETUDIANT="testetudiant";

   //enseignant
   public static final String ENSEIGNANT="eneignant";
   public static final String ENSEIGNANT_ID="id_enseignant";
   public static final String ENSEIGNANT_NOM="nom_enseignant";
   public static final String ENSEIGNANT_COURS="cours";

   //seance
   public static final String SEANCE ="seance";
   public static final String SEANCE_ID ="id_seance";
   public static final String SEANCE_DATE ="date_seance";
   public static final String SEANCE_DUREE ="duree_seance";
   public static final String SEANCE_COURS ="cours_seance";

   //COURS etudiant
   public static final String COURS_ETUDIANT ="CoursEtudiant";
   public static final String COURS_ETUDIANT_ID="id_cours_etudiant";
   public static final String COURS_ETUDIANT_COURS_ASSO ="cours_asso";
   public static final String COURS_ETUDIANT_ETUDIANT_ASSO ="etudiant_asso";

   //test etudiant
   public static final String TEST_ETUDIANT ="TestEtudiant";
   public static final String TEST_ETUDIANT_ID ="id_test_etudiant";
   public static final String TEST_ETUDIANT_TEST_ASSO ="test_asso";
   public static final String TEST_ETUDIANT_ASS0_TEST ="etudiant_asso_test";
   public static final String TEST_ETUDIANT_NOTE ="note_test";




}
