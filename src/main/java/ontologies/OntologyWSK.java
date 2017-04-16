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

         // ------- Add Concepts

         // Cours
         ConceptSchema cs = new ConceptSchema(COURS);
         add(cs, Cours.class);
         cs.add(COURS_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(COURS_INTITULE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(COURS_DUREE , (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         

         
         // TEST
         cs = new ConceptSchema(TEST);
         add(cs, Test.class);
         cs.add(TEST_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(TEST_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(TEST_DUREE , (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(TEST_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         
         //PROBLEM
         cs = new ConceptSchema(PROBLEM);
         add(cs, Problem.class);
         cs.add(PROBLEM_MSG, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(PROBLEM_NUM, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
       
         
         

         // ------- Add AgentActions

         // CreateCours
         AgentActionSchema as = new AgentActionSchema(CREATE_COURS);
         add(as, CreateCours.class);
         as.add(CREATE_COURS_INTITULE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_DUREE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

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
         
         
      }
      catch (OntologyException oe) {
         oe.printStackTrace();
      }
   }
}
