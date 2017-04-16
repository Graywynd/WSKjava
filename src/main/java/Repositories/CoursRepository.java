package Repositories;

import ontologies.Cours;
import org.hibernate.Session;
import org.hibernate.Transaction;


import Utilities.HibernateUtil;

/**
 * Created by Khalil on 16/04/2017.
 */
public class CoursRepository implements  ICoursRepository{

    @Override
    public Boolean create(Cours c) {
        boolean a_retourner = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            session.save(c);
            transaction.commit();

            a_retourner = true;
        } catch (Exception e) {
            System.out.println("LOG : Exception lors de la creation. Détails :"
                    + e);
            if ((transaction != null) && transaction.isActive())
                transaction.rollback();
        }

        return a_retourner;
    }

    public Cours findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Cours c = (Cours) session.get(Cours.class, id);

        // Clean up !
        session.close();

        return c;
    }

}
