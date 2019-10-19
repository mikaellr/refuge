package be.iepscf.refuge.persistence.dao.voldemort;

import be.iepscf.refuge.persistence.dao.SpeciesDAO;
import be.iepscf.refuge.persistence.entitybean.Species;

public class VoldemortSpeciesDAO extends VoldemortGenericDAO<Species, Long> implements SpeciesDAO {

	@Override
	public Species findByName(String name) {
		return (Species) findBy("name", name);
	}

}
