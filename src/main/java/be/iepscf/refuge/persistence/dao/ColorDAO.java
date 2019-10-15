package be.iepscf.refuge.persistence.dao;

import be.iepscf.refuge.persistence.entitybean.Color;

public interface ColorDAO extends GenericDAO<Color, Long> {

    public Color findByName(String name);

}
