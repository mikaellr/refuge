package be.iepscf.refuge.persistence.dao;

import be.iepscf.refuge.persistence.dao.jdbc.JdbcDAOFactory;

public abstract class DAOFactory {

    private static Class<JdbcDAOFactory> defaultDAOFactoryClass = JdbcDAOFactory.class;

    public static DAOFactory getDAOFactory() {
        return getDAOFactory(defaultDAOFactoryClass);
    }

    public static DAOFactory getDAOFactory(Class factory) {
        try {
            return (DAOFactory) factory.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }

    public abstract AnimalDAO getAnimalDAO();
    public abstract ColorDAO getColorDAO();
    public abstract ContactRequestDAO getContactRequestDAO();
    public abstract RaceDAO getRaceDAO();
    public abstract RoleDAO getRoleDAO();
    public abstract SpeciesDAO getSpeciesDAO();
    public abstract UserDAO getUserDAO();

}