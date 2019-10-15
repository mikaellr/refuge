package be.iepscf.refuge.persistence.dao.voldemort;


import be.iepscf.refuge.persistence.dao.ContactRequestDAO;
import be.iepscf.refuge.persistence.entitybean.ContactRequest;

public class VoldemortContactRequestDAO extends GenericHibernateDAO<ContactRequest, Long> implements ContactRequestDAO {

}
