package Repositories;

import Utilities.HibernateUtil;
import ontologies.Cours;
import ontologies.CoursEtudiant;
import ontologies.Etudiant;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by saif on 17/04/2017.
 */
public class CoursEtudiantRepository implements ICoursEtudiantRepository {

    @Override
    public Boolean AffecterEtudiant_Cours(int id_cours,int id_etudiant){
        boolean a_retourner = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            Cours cr = (Cours) session.get(Cours.class,id_cours);
            Etudiant et = (Etudiant) session.get(Etudiant.class,id_etudiant);

            CoursEtudiant cret = new CoursEtudiant();
            cret.setCours_asso(cr);
            cret.setEtudiant_asso(et);

            if(cr!=null && et!=null)
            session.save(cret);

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

}
