package be.iepscf.refuge.persistence.dao;


import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.ContactRequest;

import java.util.List;

public interface ContactRequestDAO extends GenericDAO<ContactRequest, Long> {

    public List<ContactRequest> findByAnimal(Animal animal);

}
