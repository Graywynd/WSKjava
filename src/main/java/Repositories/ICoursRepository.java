package Repositories;

import ontologies.Cours;

/**
 * Created by Khalil on 16/04/2017.
 */
public interface ICoursRepository {

    Boolean create(Cours c);
    Cours findById(int id);
}
