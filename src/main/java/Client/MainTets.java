/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

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
                AgentController ac2;
		try {
			ac = cc.createNewAgent("Server","Agents.AgentServer", null);
			ac.start();
		} catch (StaleProxyException e1) {
			
			e1.printStackTrace();
			
		}
                
                try {
			ac2 = cc.createNewAgent("Enseignant","Agents.AgentEnseignant", null);
			ac2.start();
                        
		} catch (StaleProxyException e1) {
			
			e1.printStackTrace();
			
		}
                
                
                
                
		
	}
        
    }
    

