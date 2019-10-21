package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.dao.*;


public class JdbcDAOFactory extends DAOFactory {


    public AnimalDAO getAnimalDAO() {
        return new JdbcAnimalDAO();
    }

    public ColorDAO getColorDAO() {
        return new JdbcColorDAO();
    }

    public ContactRequestDAO getContactRequestDAO(){
        return new JdbcContactRequestDAO();
    }

    public RaceDAO getRaceDAO(){
        return new JdbcRaceDAO();
    }

    public RoleDAO getRoleDAO(){
        return new JdbcRoleDAO();
    }

    public SpeciesDAO getSpeciesDAO(){
        return new JdbcSpeciesDAO();
    }

    public JdbcUserDAO getUserDAO(){
        return new JdbcUserDAO();
    }

}