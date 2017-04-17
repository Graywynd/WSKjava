package Repositories;

import ontologies.Cours;

/**
 * Created by saif on 17/04/2017.
 */
public interface ICoursEtudiantRepository {

    Boolean AffecterEtudiant_Cours(int id_cours,int id_etudiant);
}
