/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

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

import ontologies.*;

/**
 *
 * @author saif
 */
public class AgentServer extends Agent implements Vocabulary {
    
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
                  //else if (action instanceof MakeOperation)
                  //   addBehaviour(new HandleOperation(myAgent, msg));
                  else
                     replyNotUnderstood(msg);
                  break;

               case (ACLMessage.QUERY_REF):

                  System.out.println("message server : Query from " + msg.getSender().getLocalName());

                  if (action instanceof InformationCours)
                     addBehaviour(new HandleInformationCours(myAgent, msg));
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
            int id = generateId();
            cours.setId_cours(idCnt);
            cours.setIntitule(ca.getIntitule());
            Result result = new Result((Action)content,  (Cours)cours);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            
            listcours.put(id, cours);
            //operations.put(id, new ArrayList());
            System.out.println("Cours [" + cours.getIntitule() + " # " +
                               cours.getId_cours() + "] created!");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

  /* class HandleOperation extends OneShotBehaviour {
// ------------------------------------------------  Handler for an Operation request

      private ACLMessage request;

      HandleOperation(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            MakeOperation mo = (MakeOperation)((Action)content).getAction();
            Object obj = processOperation(mo);
            if (obj == null) replyNotUnderstood(request);
            else {
               ACLMessage reply = request.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, (List) obj);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("Operation processed.");
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   } */

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
      System.out.println("size :"+  listcours.size());
      System.out.println("id cours :" +info.getId_Cours());
      Cours crs = (Cours)listcours.get(info.getId_Cours());
      if (crs == null) return newProblem(COURS_INTROUVABLE);
      return crs;
     /* java.util.Date date = new java.util.Date();
      Operation op = new Operation();              // <-- Apply admin charge
      op.setType(ADMIN);
      op.setAmount(info.getType()==BALANCE ? BAL_CHARGE : OPER_CHARGE);
      acc.setBalance(acc.getBalance() - op.getAmount());
      op.setBalance(acc.getBalance());
      op.setAccountId(acc.getId());
      op.setDate(date);
      List l = (List)operations.get(acc.getId());
      l.add(op);
      operations.put(acc.getId(), l);

      if (info.getType() == BALANCE) return crs;
      if (info.getType() == OPERATIONS) return l;*/

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
