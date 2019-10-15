package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.dao.*;


public class JdbcDAOFactory extends DAOFactory {


    public AnimalDAO getAnimalDAO() {
        return new JdbcAnimalDAO();
    }

    public ColorDAO getColorDAO() {
        return null;
    }

    public ContactRequestDAO getContactRequestDAO(){
        return null;
    }

    public RaceDAO getRaceDAO(){
        return null;
    }

    public RoleDAO getRoleDAO(){
        return null;
    }

    public SpeciesDAO getSpeciesDAO(){
        return new JdbcSpeciesDAO();
    }

    public JdbcUserDAO getUserDAO(){
        return new JdbcUserDAO();
    }

}