/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Agents.AgentOperator;
import Repositories.CoursEtudiantRepository;
import Repositories.CoursRepository;
import Repositories.ICoursRepository;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.*;
import ontologies.Cours;
import ontologies.Enseignant;
import java.util.List;

/**
 *
 * @author saif
 */

public class MainTets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
      
   /*   Runtime rt = Runtime.instance();
		Profile p = new ProfileImpl();
		p.setParameter(Profile.MAIN_HOST"localhost");
		p.setParameter(Profile.GUI, "true");
		p.setParameter(Profile.LOCAL_PORT,"12344");
		ContainerController cc = rt.createMainContainer(p);
		AgentController ac;

		try {
			ac = cc.createNewAgent("Server","Agents.AgentServer", null);
			ac.start();
		} catch (StaleProxyException e1) {
			
			e1.printStackTrace();
			
		}


		try {
			ac = cc.createNewAgent("Enseignant","Agents.AgentEnseignant", new Object[0]);
			ac.start();


			State state = ac.getState();
			while (!(state.getCode() == AgentState.cAGENT_STATE_IDLE)){
				Thread.sleep(100); // wait 100 miliseconds and try it again...
				state = ac.getState();

			}
			ac.putO2AObject("createcours",AgentController.ASYNC);

		} catch (StaleProxyException e1) {

			e1.printStackTrace();

		}*/

		AgentOperator operator = new AgentOperator();
		operator.start_agents();
		operator.send_to_enseignant("listcours");






		/*CoursEtudiantRepository repo = new CoursEtudiantRepository();





		System.out.println(repo.AffecterEtudiant_Cours(1,1)); */


                
                
                
                
		
	}
        
    }
    

