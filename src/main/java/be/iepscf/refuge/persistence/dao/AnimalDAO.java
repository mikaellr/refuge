package be.iepscf.refuge.persistence.dao;

import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public interface AnimalDAO extends GenericDAO<Animal, Long> {

    public List<Animal> findBySpecies(Long id);

    public List<Animal> findByRaces(Long id);

    public  List<Animal> findAllAdoptable();

    public List<Animal> findMultiParameters(Long species, Long race, Boolean is_adopted, Boolean all, Boolean last, Long limit, Long offset);
	
}
