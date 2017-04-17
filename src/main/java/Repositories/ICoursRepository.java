package Repositories;


import ontologies.Cours;

import java.util.*;

/**
 * Created by Khalil on 16/04/2017.
 */
public interface ICoursRepository {

    Boolean create(Cours c);
    Cours findById(int id);
    ArrayList<Cours> findByEnseignant(int id_enseignant);
}
