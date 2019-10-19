package be.iepscf.refuge.persistence.service;

import be.iepscf.refuge.persistence.dao.jdbc.JdbcDAOFactory;
import be.iepscf.refuge.persistence.dao.voldemort.VoldemortDAOFactory;
import be.iepscf.refuge.persistence.entitybean.*;
import be.iepscf.refuge.persistence.dao.*;

import java.util.List;


public class BeanService {

    DAOFactory jdbcDAOFactory;
    DAOFactory voldemortDAOFactory;

    protected DAOFactory getDAOFactory() {
        return getJdbcDAOFactory();
    }

    protected DAOFactory getJdbcDAOFactory() {
        if (jdbcDAOFactory == null) {
             jdbcDAOFactory = DAOFactory.getDAOFactory(JdbcDAOFactory.class);
        }
        return jdbcDAOFactory;
    }

    protected DAOFactory getVoldemortDAOFactory() {
        if (voldemortDAOFactory == null) {
            voldemortDAOFactory =  DAOFactory.getDAOFactory(VoldemortDAOFactory.class);
        }
        return voldemortDAOFactory;
    }




    /* DAOs : (public for testability ?) */
    /* !!!!!!! remplacer ici getVoldemorDAOFactory() par getDAOFactory() quand le JdbcDAO correspondant est prêt
    (tests unitaires sur ses méthodes, idéalement) */

    public UserDAO getUserDAO() {
        return (UserDAO) getDAOFactory().getUserDAO();
    }

    public RoleDAO getRoleDAO() {
        return (RoleDAO) getVoldemortDAOFactory().getRoleDAO();
    }

    public AnimalDAO getAnimalDAO() {
        return (AnimalDAO) getDAOFactory().getAnimalDAO();
    }

    public SpeciesDAO getSpeciesDAO() {
        return (SpeciesDAO) getVoldemortDAOFactory().getSpeciesDAO();
    }

    public RaceDAO getRaceDAO() {
        return (RaceDAO) getVoldemortDAOFactory().getRaceDAO();
    }

    public ColorDAO getColorDAO() {
        return (ColorDAO) getVoldemortDAOFactory().getColorDAO();
    }

    public ContactRequestDAO getContactRequestDAO() {
        return (ContactRequestDAO) getVoldemortDAOFactory().getContactRequestDAO();
    }






    /* User : */

    public User getUser(Long id) {
        return getUserDAO().find(id);
    }

    public User getUserByEmail(String email) {
        return getUserDAO().findByEmail(email);
    }

    public List<User> getUsers() {
        return getUserDAO().findAll();
    }

    public long saveUser(User item) {
        return getUserDAO().save(item);
    }

    public long updateUser(User item) {
        return getUserDAO().update(item);
    }

    public long deleteUser(User item) {
        return getUserDAO().delete(item);
    }



    /* Role : */

    public List<Role> getRoles() {
        return getRoleDAO().findAll();
    }

    public Role getRole(Long id) {
        return getRoleDAO().find(id);
    }

    public long saveRole(Role item) {
        return getRoleDAO().save(item);
    }

    public long updateRole(Role item) {
        return getRoleDAO().update(item);
    }

    public long deleteRole(Role item) {
        return getRoleDAO().delete(item);
    }

    public Role getEmployeRole() {
        return getRoleDAO().find(1L);
    }


    /* Animal : */

    public Animal getAnimal(Long id) {
        return getAnimalDAO().find(id);
    }

    public List<Animal> getAnimals() {
        return getAnimalDAO().findAll();
    }

    public List<Animal> getAnimalsByParameters(Long species, Long race, Boolean is_adopted, Boolean all, Boolean last, Long limit, Long offset) {
        return getAnimalDAO().findMultiParameters(species, race, is_adopted, all, last, limit, offset);
    }

    public long saveAnimal(Animal item) {
        return getAnimalDAO().save(item);
    }

    public long updateAnimal(Animal item) {
        return getAnimalDAO().update(item);
    }

    public long deleteAnimal(Animal item) {
        return getAnimalDAO().delete(item);
    }



    /* Species : */

    public Species getSpecies(Long id) {
        return getSpeciesDAO().find(id);
    }

    public Species getSpeciesByName(String name) {
        return getSpeciesDAO().findByName(name);
    }

    public List<Species> getSpecies() { return getSpeciesDAO().findAll(); }

    public List<Race> getRacesBySpecies(Species species) {
        return getSpeciesDAO().getRacesBySpecies(species);
    }

    public long saveSpecies(Species item) {
        return getSpeciesDAO().save(item);
    }

    public long updateSpecies(Species item) {
        return getSpeciesDAO().update(item);
    }

    public long deleteSpecies(Species item) {
        return getSpeciesDAO().delete(item);
    }



    /* Race : */

    public Race getRace(Long id) {
        return getRaceDAO().find(id);
    }

    public List<Race> getRaces() {
        return getRaceDAO().findAll();
    }

    public long saveRace(Race item) {
        return getRaceDAO().save(item);
    }

    public long updateRace(Race item) {
        return getRaceDAO().update(item);
    }

    public long deleteRace(Race item) {
        return getRaceDAO().delete(item);
    }




    /* Color : */

    public Color getColor(Long id) {
        return getColorDAO().find(id);
    }

    public List<Color> getColors() {
        return getColorDAO().findAll();
    }

    public Color getColorByName(String name) {
        return getColorDAO().findByName(name);
    }

    public long saveColor(Color item) {
        return getColorDAO().save(item);
    }

    public long updateColor(Color item) {
        return getColorDAO().update(item);
    }

    public long deleteColor(Color item) {
        return getColorDAO().delete(item);
    }



    /* ContactRequest : */

    public ContactRequest getContactRequest(Long id) {
        return getContactRequestDAO().find(id);
    }

    public List<ContactRequest> getContactRequests() {
        return getContactRequestDAO().findAll();
    }

    public long saveContactRequest(ContactRequest item) {
        return getContactRequestDAO().save(item);
    }

    public long updateContactRequest(ContactRequest item) {
        return getContactRequestDAO().update(item);
    }

    public long deleteContactRequest(ContactRequest item) {
        return getContactRequestDAO().delete(item);
    }

}
