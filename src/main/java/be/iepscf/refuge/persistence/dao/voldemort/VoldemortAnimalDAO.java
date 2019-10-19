package be.iepscf.refuge.persistence.dao.voldemort;


import be.iepscf.refuge.persistence.dao.AnimalDAO;
import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public class VoldemortAnimalDAO extends VoldemortGenericDAO<Animal, Long> implements AnimalDAO {

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

    @Override
    public List<Animal> findMultiParameters(Long species, Long race, Boolean is_adopted, Boolean all, Boolean last, Long limit, Long offset) {
        return null;
    }
}
