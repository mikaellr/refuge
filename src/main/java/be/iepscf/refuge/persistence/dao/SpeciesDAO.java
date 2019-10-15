package be.iepscf.refuge.persistence.dao;


import be.iepscf.refuge.persistence.entitybean.Species;

public interface SpeciesDAO extends GenericDAO<Species, Long> {

	public Species findByName(String name);
	
}
