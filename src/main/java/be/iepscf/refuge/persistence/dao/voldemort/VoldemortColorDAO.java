package be.iepscf.refuge.persistence.dao.voldemort;


import be.iepscf.refuge.persistence.dao.ColorDAO;
import be.iepscf.refuge.persistence.entitybean.Color;

public class VoldemortColorDAO extends VoldemortGenericDAO<Color, Long> implements ColorDAO {

	@Override
	public Color findByName(String name) {
		return (Color) findBy("name", name);
	}

}
