package Repositories;

import Utilities.HibernateUtil;
import ontologies.Cours;
import ontologies.Enseignant;
import org.hibernate.Session;

/**
 * Created by saif on 17/04/2017.
 */
public class EnseignantRepository implements  IEnseignantRepository {

    @Override
   public Enseignant findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Enseignant c = (Enseignant) session.get(Enseignant.class, id);

        // Clean up !
        session.close();

        return c;
    }
}
