package be.iepscf.refuge.persistence.dao.voldemort;


import be.iepscf.refuge.persistence.dao.ContactRequestDAO;
import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.ContactRequest;

import java.util.List;

public class VoldemortContactRequestDAO extends GenericHibernateDAO<ContactRequest, Long> implements ContactRequestDAO {

    @Override
    public List<ContactRequest> findByAnimal(Animal animal) {
        return null;
    }
}
