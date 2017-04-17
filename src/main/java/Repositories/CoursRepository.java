package Repositories;

import ontologies.Cours;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import  java.util.*;


import Utilities.HibernateUtil;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    @Override
    public Cours findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Cours c = (Cours) session.get(Cours.class, id);

        // Clean up !
        session.close();

        return c;
    }

    @Override
    public ArrayList<Cours> findByEnseignant(int id_enseignant){

        ArrayList<Cours> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        Criteria cr = session.createCriteria(Cours.class);
        cr.add(Restrictions.eq("enseignant.id_enseignant",id_enseignant));

        // Work with the session
         lc =  (ArrayList<Cours>) cr.list();

        // Clean up !
        session.close();

        return lc;


    }



}
