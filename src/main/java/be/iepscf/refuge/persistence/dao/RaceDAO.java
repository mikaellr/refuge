package be.iepscf.refuge.persistence.dao;


import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public interface RaceDAO extends GenericDAO<Race, Long> {

    public List<Race> findBySpecies(Species species);

}
