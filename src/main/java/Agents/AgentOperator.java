package Agents;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.*;

/**
 * Created by saif on 24/04/2017.
 */
public class AgentOperator {

    Runtime runtime = null ;
    Profile profile =  null;
    ContainerController maincontainer = null;

    AgentController servercontroller= null;
    AgentController enseignantcontroller= null;
    AgentController etudiantcontroller= null;

    public void start_agents(){

        runtime = Runtime.instance();
        profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST,"localhost");
        profile.setParameter(Profile.GUI, "true");
        profile.setParameter(Profile.LOCAL_PORT,"12344");
        maincontainer = runtime.createMainContainer(profile);

        try {
            servercontroller = maincontainer.createNewAgent("Server","Agents.AgentServer", null);
            servercontroller.start();

            enseignantcontroller = maincontainer.createNewAgent("Enseignant","Agents.AgentEnseignant", new Object[0]);
            enseignantcontroller.start();

            etudiantcontroller = maincontainer.createNewAgent("Etudiant","Agents.AgentEtudiant", new Object[0]);
            etudiantcontroller.start();


        } catch (StaleProxyException e1) {

            e1.printStackTrace();

        }
    }

    public void send_to_enseignant(Object obj){

        try {

            State state = enseignantcontroller.getState();
            while (!(state.getCode() == AgentState.cAGENT_STATE_IDLE)) {
                Thread.sleep(100); // wait 100 miliseconds and try it again...
                state = enseignantcontroller.getState();

            }
            enseignantcontroller.putO2AObject(obj, AgentController.ASYNC);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
