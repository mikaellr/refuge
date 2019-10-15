package be.iepscf.refuge.persistence.dao.voldemort;

import be.iepscf.refuge.persistence.dao.*;

public class VoldemortDAOFactory extends DAOFactory {

	
	public GenericHibernateDAO createDAO(Class daoClass) {
		try {
			return (GenericHibernateDAO) daoClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Coudln't create DAO : " + daoClass);
		}
	}
	
	@Override
	public AnimalDAO getAnimalDAO() {
		return (AnimalDAO) createDAO(VoldemortAnimalDAO.class);
	}

	@Override
	public ColorDAO getColorDAO() {
		return (ColorDAO) createDAO(VoldemortColorDAO.class);
	}

	@Override
	public ContactRequestDAO getContactRequestDAO() {
		return (ContactRequestDAO) createDAO(VoldemortContactRequestDAO.class);
	}

	@Override
	public RaceDAO getRaceDAO() {
		return (RaceDAO) createDAO(VoldemortRaceDAO.class);
	}

	@Override
	public RoleDAO getRoleDAO() {
		return (RoleDAO) createDAO(VoldemortRoleDAO.class);
	}

	@Override
	public SpeciesDAO getSpeciesDAO() {
		return (SpeciesDAO) createDAO(VoldemortSpeciesDAO.class);
	}

	@Override
	public UserDAO getUserDAO() {
		return (UserDAO) createDAO(VoldemortUserDAO.class);
	}


	// inline empty DAO implementations :
	//public static class WtfHibernateDAO extends GenericHibernateDAO<Marque, Long> implements UserDAO {};
	
}
