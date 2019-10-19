package be.iepscf.refuge.persistence.dao.voldemort;

import be.iepscf.refuge.persistence.dao.SpeciesDAO;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import java.util.List;

public class VoldemortSpeciesDAO extends VoldemortGenericDAO<Species, Long> implements SpeciesDAO {

	@Override
	public Species findByName(String name) {
		return (Species) findBy("name", name);
	}

}
