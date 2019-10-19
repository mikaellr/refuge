package be.iepscf.refuge.persistence.dao.voldemort;


import be.iepscf.refuge.persistence.dao.AnimalDAO;
import be.iepscf.refuge.persistence.entitybean.Animal;

public class VoldemortAnimalDAO extends VoldemortGenericDAO<Animal, Long> implements AnimalDAO {

}
