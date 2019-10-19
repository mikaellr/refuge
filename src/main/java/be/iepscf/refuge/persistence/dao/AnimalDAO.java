package be.iepscf.refuge.persistence.dao;

import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public interface AnimalDAO extends GenericDAO<Animal, Long> {

    public List<Animal> findBySpecies(Species species);

    public List<Animal> findByRace(Race race);

    public  List<Animal> findAllAdoptable();
	
}
