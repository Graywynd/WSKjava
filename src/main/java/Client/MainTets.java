/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

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
		ContainerController cc = rt.createMainContainer(p);
		AgentController ac;

		try {
			ac = cc.createNewAgent("Server","Agents.AgentServer", null);
			ac.start();
		} catch (StaleProxyException e1) {
			
			e1.printStackTrace();
			
		}



                try {
			ac = cc.createNewAgent("Enseignant","Agents.AgentEnseignant", null);
			ac.start();



		} catch (StaleProxyException e1) {

			e1.printStackTrace();

		}

		/*ICoursRepository repo = new CoursRepository();
		Enseignant enseignant = new Enseignant();
		enseignant.setId_enseignant(1);
		enseignant.setNom_enseignant("m romdhani");
		Cours cours = new Cours();
		cours.setId_cours(2);
		cours.setDuree(10);
		cours.setIntitule("cours1");
		cours.setEnseignant(enseignant);
		repo.create(cours);
		Cours cours2 ;
		cours2 = repo.findById(2);
		System.out.println(cours2);*/


                
                
                
                
		
	}
        
    }
    

