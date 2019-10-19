package be.iepscf.refuge.persistence.dao;


import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public interface SpeciesDAO extends GenericDAO<Species, Long> {

	public Species findByName(String name);

	public List<Race> getRacesBySpecies(Species species);
	
}
