package be.iepscf.refuge.persistence.dao.voldemort;

import be.iepscf.refuge.persistence.dao.RaceDAO;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public class VoldemortRaceDAO extends GenericHibernateDAO<Race, Long> implements RaceDAO {

    @Override
    public List<Race> findBySpecies(Species species) {
        return null;
    }
}
