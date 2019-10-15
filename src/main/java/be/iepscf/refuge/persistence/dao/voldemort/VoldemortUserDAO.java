package be.iepscf.refuge.persistence.dao.voldemort;


import be.iepscf.refuge.persistence.dao.UserDAO;
import be.iepscf.refuge.persistence.entitybean.User;

import javax.persistence.NoResultException;

/**
 * 
 * 
 * pour exemple, copié/collé d'un autre projet (Projet SGBD Garage 2018-2019), à modifier
 *
 *
 */
public class VoldemortUserDAO extends GenericHibernateDAO<User, Long> implements UserDAO {

	@Override
	public User findByEmail(String email) {
		try {
			return (User) getEntityManager()
					.createQuery("select item from User item where item.email=:email")
					.setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {
		}
		return null;
	}

/*	public List<User> findAllAlphabetical() {
		 Query query = getEntityManager().createQuery(String.format("SELECT item FROM %s item ORDER BY item.numeroChassis", getEntityName()));
		 return (List<User>) query.getResultList();
	}	

	public List<User> getLastUser(int max) {
		String sql = String.format("SELECT item FROM %s item ORDER BY item.dateDepot ASC", getEntityName());
		Query query = getEntityManager().createQuery(sql).setMaxResults(max);
		return (List<User>) query.getResultList();
	}


	public User getByLogin(String login) {
		try {
			return (User) getEntityManager()
					.createQuery("select item from User item where item.login=:login")
					.setParameter("login", login)
					.getSingleResult();
		} catch (NoResultException e) {
		}
		return null; 
	}*/

}
