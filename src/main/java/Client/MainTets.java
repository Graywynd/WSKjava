/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Repositories.CoursEtudiantRepository;
import Repositories.CoursRepository;
import Repositories.ICoursRepository;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
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
    public static void main(String[] args) {
      
     Runtime rt = Runtime.instance();
		Profile p = new ProfileImpl();
		p.setParameter(Profile.MAIN_HOST, "localhost");
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






		/*CoursEtudiantRepository repo = new CoursEtudiantRepository();





		System.out.println(repo.AffecterEtudiant_Cours(1,1)); */


                
                
                
                
		
	}
        
    }
    

