/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;


import Repositories.CoursEtudiantRepository;
import Repositories.CoursRepository;
import Repositories.ICoursEtudiantRepository;
import Repositories.ICoursRepository;
import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.*;
import jade.content.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
import jade.content.onto.basic.*;
import jade.util.leap.*;


import jade.util.leap.ArrayList;
import jade.util.leap.HashMap;
import jade.util.leap.Map;
import ontologies.*;

import java.util.*;

/**
 *
 * @author saif
 */
public class AgentServer extends Agent implements Vocabulary {

   private ICoursRepository repo = new CoursRepository();
   private ICoursEtudiantRepository repo_coursetudiant = new CoursEtudiantRepository();
    
   private int idCnt = 0;
   private Map listcours = new HashMap();
   private Map operations = new HashMap();
   private Codec codec = new SLCodec();
   private Ontology ontology = OntologyWSK.getInstance();

   protected void setup() {
// ------------------------

      // Register language and ontology
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontology);

      // Set this agent main behaviour
      SequentialBehaviour sb = new SequentialBehaviour();
      sb.addSubBehaviour(new RegisterInDF(this));
      sb.addSubBehaviour(new ReceiveMessages(this));
      addBehaviour(sb);
   }

   class RegisterInDF extends OneShotBehaviour {
// ---------------------------------------------  Register in the DF for the client agent
//                                                be able to retrieve its AID
      RegisterInDF(Agent a) {
         super(a);
      }

      public void action() {

         ServiceDescription sd = new ServiceDescription();
         sd.setType(SERVER_AGENT);
         sd.setName(getName());
         sd.setOwnership("Prof6802");
         DFAgentDescription dfd = new DFAgentDescription();
         dfd.setName(getAID());
         dfd.addServices(sd);
         try {
            DFAgentDescription[] dfds = DFService.search(myAgent, dfd);
            if (dfds.length > 0 ) {
               DFService.deregister(myAgent, dfd);
            }
            DFService.register(myAgent, dfd);
            System.out.println(getLocalName() + " is ready.");
         }
         catch (Exception ex) {
            System.out.println("Failed registering with DF! Shutting down...");
            ex.printStackTrace();
            doDelete();
         }
      }
   }

   class ReceiveMessages extends CyclicBehaviour {
// -----------------------------------------------  Receive requests and queries from client
//                                                  agent and launch appropriate handlers

      public ReceiveMessages(Agent a) {

         super(a);
      }

      public void action() {

         ACLMessage msg = receive();
         if (msg == null) { block(); return; }
         try {
            ContentElement content = getContentManager().extractContent(msg);
            Concept action = ((Action)content).getAction();

            switch (msg.getPerformative()) {

               case (ACLMessage.REQUEST):

                  System.out.println("message srver :Request from " + msg.getSender().getLocalName());

                  if (action instanceof CreateCours)
                     addBehaviour(new HandleCreateCours(myAgent, msg));
                  else if (action instanceof AffecterCours)
                    addBehaviour(new HandleAffecterCours(myAgent, msg));
                  else
                     replyNotUnderstood(msg);
                  break;

               case (ACLMessage.QUERY_REF):

                  System.out.println("message server : Query from " + msg.getSender().getLocalName());

                  if (action instanceof InformationCours)
                     addBehaviour(new HandleInformationCours(myAgent, msg));
                  else if (action instanceof ListCoursEnseignant)
                     addBehaviour(new HandleListCoursEnseignant(myAgent, msg));
                  else
                     replyNotUnderstood(msg);
                  break;

               default:
                     replyNotUnderstood(msg);
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleCreateCours extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateCours(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateCours ca = (CreateCours)((Action)content).getAction();
            Cours cours = new Cours();

            cours.setIntitule(ca.getIntitule());
            cours.setEnseignant(ca.getEnseignant());
            Result result = new Result((Action)content,  (Cours)cours);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            repo.create(cours);
            System.out.println("Cours [" + cours.getIntitule() + " # " +
                               cours.getId_cours() + "] created!");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleInformationCours extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleInformationCours(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);
            InformationCours info = (InformationCours)((Action)content).getAction();
            Object obj = processInformation(info);
            if (obj == null) replyNotUnderstood(query);
            else {
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, obj);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("information du cours " + obj  + "from server");
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleAffecterCours extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleAffecterCours(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(query);
            AffecterCours cours_aff = (AffecterCours)((Action)content).getAction();
            Boolean res = repo_coursetudiant.AffecterEtudiant_Cours(cours_aff.getId_cours(),cours_aff.getId_etudiant());
            if (!res){
               String reponse = "Affectation du cours d'id "+cours_aff.getId_cours()+" à l'etudiant d'id "+cours_aff.getId_etudiant()+" a échoue";
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, (String)reponse);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("affectation cours de serveur");
            }
            else {
               String reponse = "Cours d'id "+cours_aff.getId_cours()+" a ete affecté à l'etudiant d'id"+cours_aff.getId_etudiant();
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, (String)reponse);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("affectation cours de serveur");
            }

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListCoursEnseignant extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListCoursEnseignant(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListCoursEnseignant ca = (ListCoursEnseignant) ((Action)content).getAction();
            java.util.ArrayList<Cours> listcours = null;
            listcours = repo.findByEnseignant(ca.getId_enseignant());
            ArrayList listcoursjade = new ArrayList(listcours);
            Result result = new Result((Action)content,  (ArrayList)listcoursjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List cours retournée");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   void replyNotUnderstood(ACLMessage msg) {
// -----------------------------------------

      try {
         ContentElement content = getContentManager().extractContent(msg);
         ACLMessage reply = msg.createReply();
         reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
         getContentManager().fillContent(reply, content);
         send(reply);
         System.out.println("Not understood!");
      }
      catch(Exception ex) { ex.printStackTrace(); }
   }

  

   Object processInformation(InformationCours info) {
// -------------------------------------------

   //   Cours crs = (Cours)listcours.get(info.getId_Cours());
      Cours crs = repo.findById(info.getId_Cours());
      if (crs == null) return newProblem(COURS_INTROUVABLE);
      return crs;


   }

   Object processAffectationCours(AffecterCours cours_aff){
      //traitement pour ajouter un etudiant a un cours
      return null ;
   }

//--------------------------- Utility methods ----------------------------//

   Problem newProblem(int num) {
// -----------------------------

      String msg = "";

      if (num == COURS_INTROUVABLE)
         msg = MSG_COURS_INTOUVABLE;

     // else if (num == NOT_ENOUGH_MONEY)
     //    msg = PB_NOT_ENOUGH_MONEY;

     // else if (num == ILLEGAL_OPERATION)
     //    msg = PB_ILLEGAL_OPERATION;

      Problem prob = new Problem();
      prob.setNum(num);
      prob.setMsg(msg);
      return prob;
   }

   int generateId() {
// ---------------------
      idCnt++;
      return idCnt;
   }

    
}
