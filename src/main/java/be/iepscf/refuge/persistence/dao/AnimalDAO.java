package be.iepscf.refuge.persistence.dao;

import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public interface AnimalDAO extends GenericDAO<Animal, Long> {

    public List<Animal> findBySpecy(Species specy);
	
}
