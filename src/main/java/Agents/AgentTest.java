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
import jade.gui.*;
import ontologies.*;

/**
 *
 * @author saif
 */
public class AgentTest extends Agent implements Vocabulary,IAgentTest{
    
     private AID server;
    private Codec codec = new SLCodec();
   private Ontology ontology = OntologyWSK.getInstance();
    
     protected void setup() {
// ------------------------

      // Register language and ontology
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontology);

     
      
   }

   protected void takeDown() {
// ---------------------------  Terminate the program properly

       System.out.println(getLocalName() + " is now shutting down.");
       
    }
   
   
   
   
    class WaitServerResponse extends ParallelBehaviour {
// ----------------------------------------------------  launch a SimpleBehaviour to receive
//                                                       servers response and a WakerBehaviour
//                                                       to terminate the waiting if there is
//                                                       no response from the server
      WaitServerResponse(Agent a) {

         super(a, 1);

         addSubBehaviour(new AgentTest.ReceiveResponse(myAgent));

         addSubBehaviour(new WakerBehaviour(myAgent, 5000) {

            protected void handleElapsedTimeout() {
               //alertGui("No response from server. Please, try later!");
               //resetStatusGui();
            }
         });
      }
   }
    
    
     class ReceiveResponse extends SimpleBehaviour {
// -----------------------------------------------  // Receive and handle server responses

      private boolean finished = false;

      ReceiveResponse(Agent a) {
         super(a);
      }

      public void action() {
        /*
         ACLMessage msg = receive(MessageTemplate.MatchSender(server));

         if (msg == null) { block(); return; }

         if (msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD){
           // alertGui("Response from server: NOT UNDERSTOOD");
         }
         else if (msg.getPerformative() != ACLMessage.INFORM){
           // alertGui("\nUnexpected msg from server!");
         }
         else {
            try {
               ContentElement content = getContentManager().extractContent(msg);

               if (content instanceof Result) {

                  Result result = (Result) content;

                  if (result.getAction() instanceof Problem) {

                     Problem prob = (Problem)result.getAction();
                     alertGui(prob);
                  }
                  else if (result.getAction()  instanceof Account) {

                     Account acc = (Account) result.getAction() ;

                     if (command == NEW_ACCOUNT) {
                        accounts.add(acc);
                     }
                     alertGui(acc);
                  }
                  else if (result.getAction()  instanceof List) {
                     alertGui(result.getItems());
                  }
                  else alertGui("\nUnexpected result from server!");
               }
               else {
                  alertGui("\nUnable de decode response from server!");
               }
            }
            catch (Exception e) { e.printStackTrace(); }
         }
         resetStatusGui();
         finished = true;
   */   }

      public boolean done() { return finished; }

      public int onEnd() { return 0; }
   }
   
   
   
    void lookupServer() {
// ---------------------  Search in the DF to retrieve the server AID

      ServiceDescription sd = new ServiceDescription();
      sd.setType("Server Agent");
      DFAgentDescription dfd = new DFAgentDescription();
      dfd.addServices(sd);
      try {
         DFAgentDescription[] dfds = DFService.search(this, dfd);
         if (dfds.length > 0 ) {
            server = dfds[0].getName();
            //alertGui("Localized server");
         }
         //else alertGui("Unable to localize server. Please try later!");
      }
      catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Failed searching int the DF!");
      }
   }
   
   
    void sendMessage(int performative, AgentAction action) {
// --------------------------------------------------------
      if (server == null) lookupServer();
      if (server == null) {
         //alertGui("Unable to localize the server! Operation aborted!");
         return;
      }
      ACLMessage msg = new ACLMessage(performative);
      msg.setLanguage(codec.getName());
      msg.setOntology(ontology.getName());
      try {
         getContentManager().fillContent(msg, new Action(server, action));
         msg.addReceiver(server);
         send(msg);
         //alertGui("Contacting server... Please wait!");
         addBehaviour(new AgentTest.WaitServerResponse(this));
      }
      catch (Exception ex) { ex.printStackTrace(); }
   }
    
    
   
    
}
