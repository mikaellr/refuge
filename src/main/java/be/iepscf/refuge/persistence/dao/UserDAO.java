package be.iepscf.refuge.persistence.dao;


import be.iepscf.refuge.persistence.entitybean.User;

public interface UserDAO extends GenericDAO<User, Long> {

	public User findByEmail(String email);

}
