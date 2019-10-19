package be.iepscf.refuge.persistence.dao.voldemort;


import be.iepscf.refuge.persistence.dao.AnimalDAO;
import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public class VoldemortAnimalDAO extends GenericHibernateDAO<Animal, Long> implements AnimalDAO {

    @Override
    public List<Animal> findBySpecies(Species species) {
        return null;
    }

    @Override
    public List<Animal> findByRace(Race race) {
        return null;
    }

    @Override
    public List<Animal> findAllAdoptable() {
        return null;
    }
}
