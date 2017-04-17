/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologies;

import Agents.Vocabulary;
import jade.content.onto.*;
import jade.content.schema.*;

/**
 *
 * @author saif
 */
public class OntologyWSK extends Ontology implements Vocabulary {
    
   // ----------> The name identifying this ontology
   public static final String ONTOLOGY_NAME = "WSK-Ontology";

   // ----------> The singleton instance of this ontology
   private static Ontology instance = new OntologyWSK();

   // ----------> Method to access the singleton ontology object
   public static Ontology getInstance() { return instance; }


   // Private constructor
   private OntologyWSK() {

      super(ONTOLOGY_NAME, BasicOntology.getInstance());

      try {
          add(new ConceptSchema(COURS), Cours.class);
          add(new ConceptSchema(TEST), Test.class);
          add(new ConceptSchema(SEANCE), Seance.class);
          add(new ConceptSchema(ENSEIGNANT), Enseignant.class);
          add(new ConceptSchema(ETUDIANT), Etudiant.class);
          add(new ConceptSchema(COURS_ETUDIANT), CoursEtudiant.class);
          add(new ConceptSchema(TEST_ETUDIANT), TestEtudiant.class);
          add(new ConceptSchema(PROBLEM), Problem.class);



          // ------- Add Concepts

         // Cours
         ConceptSchema cs = (ConceptSchema) getSchema(COURS);
         add(cs, Cours.class);
         cs.add(COURS_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(COURS_INTITULE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(COURS_DUREE , (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(COURS_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class),ObjectSchema.OPTIONAL);





         // TEST
         cs  = (ConceptSchema) getSchema(TEST);
         add(cs, Test.class);
         cs.add(TEST_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(TEST_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(TEST_DUREE , (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         
         //PROBLEM
         cs =(ConceptSchema) getSchema(PROBLEM);
         add(cs, Problem.class);
         cs.add(PROBLEM_MSG, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(PROBLEM_NUM, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
       
         
         

         // ------- Add AgentActions

         // CreateCours
         AgentActionSchema as = new AgentActionSchema(CREATE_COURS);
         add(as, CreateCours.class);
         as.add(CREATE_COURS_INTITULE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_DUREE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class));

         // AffecterCours
         add(as = new AgentActionSchema(AFFECTER_COURS), AffecterCours.class);
         as.add(AFFECTER_COURS_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(AFFECTER_COURS_ID_ETUDIANT, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
        
         // CreateTest
         add(as = new AgentActionSchema(CREATE_TEST), CreateTest.class);
         as.add(CREATE_TEST_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_TEST_DUREE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         
         // PasserTest
         add(as = new AgentActionSchema(PASSER_TEST), PasserTest.class);
         as.add(PASSER_TEST_ID_TEST, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(PASSER_TEST_ID_ETUDIANT, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         
          // InformationCours
         add(as = new AgentActionSchema(INFORMATION_COURS), InformationCours.class);
         as.add(INFORMATION_COURS_TYPE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(INFORMATION_COURS_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // ListCoursEnseignant
         add(as = new AgentActionSchema(LIST_COURS_ENSEIGNANT), ListCoursEnseignant.class);
         as.add(LIST_COURS_ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);


         // Etudiant
         cs = (ConceptSchema) getSchema(ETUDIANT);
         add(cs, Etudiant.class);
         cs.add(ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(ETUDIANT_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // Enseignant
         cs =(ConceptSchema) getSchema(ENSEIGNANT);
         add(cs, Enseignant.class);
         cs.add(ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(ENSEIGNANT_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // Seance
         cs = (ConceptSchema) getSchema(SEANCE);
         add(cs, Seance.class);
         cs.add(SEANCE_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(SEANCE_DATE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(SEANCE_DUREE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // Cours Etudiant
         cs = (ConceptSchema) getSchema(COURS_ETUDIANT);
         add(cs, CoursEtudiant.class);
         cs.add(COURS_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);


         // Test Etudiant
         cs = (ConceptSchema) getSchema(TEST_ETUDIANT);
         add(cs, TestEtudiant.class);
         cs.add(TEST_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(TEST_ETUDIANT_NOTE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);







         
         
      }
      catch (OntologyException oe) {
         oe.printStackTrace();
      }
   }
}
