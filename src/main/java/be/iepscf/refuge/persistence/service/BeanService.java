package be.iepscf.refuge.persistence.service;

import be.iepscf.refuge.persistence.dao.jdbc.JdbcDAOFactory;
import be.iepscf.refuge.persistence.dao.voldemort.VoldemortDAOFactory;
import be.iepscf.refuge.persistence.entitybean.*;
import be.iepscf.refuge.persistence.dao.*;

import java.util.ArrayList;
import java.util.List;


public class BeanService {

    public static boolean DISALLOW_VOLDEMORT = true;
    public static String DISALLOW_VOLDEMORT_MESSAGE = "Vous avez prononcé le nom de Voldemort.";
    DAOFactory jdbcDAOFactory;
    //DAOFactory voldemortDAOFactory;

    protected DAOFactory getDAOFactory() {
        return getJdbcDAOFactory();
    }

    protected DAOFactory getJdbcDAOFactory() {
        if (jdbcDAOFactory == null) {
            jdbcDAOFactory = DAOFactory.getDAOFactory(JdbcDAOFactory.class);
        }
        return jdbcDAOFactory;
    }

    /*protected DAOFactory getVoldemortDAOFactory() {
        if (DISALLOW_VOLDEMORT) {
            throw new RuntimeException(DISALLOW_VOLDEMORT_MESSAGE);
        } else {
            if (voldemortDAOFactory == null) {
                voldemortDAOFactory = DAOFactory.getDAOFactory(VoldemortDAOFactory.class);
            }
            return voldemortDAOFactory;
        }
    }*/




    /* DAOs : (public for testability ?) */
    /* !!!!!!! remplacer ici getVoldemorDAOFactory() par getDAOFactory() quand le JdbcDAO correspondant est prêt
    (tests unitaires sur ses méthodes, idéalement) */

    public UserDAO getUserDAO() {
        return getDAOFactory().getUserDAO();
    }

    public RoleDAO getRoleDAO() {
        return getDAOFactory().getRoleDAO();
    }

    public AnimalDAO getAnimalDAO() {
        return getDAOFactory().getAnimalDAO();
    }

    public SpeciesDAO getSpeciesDAO() {
        return getDAOFactory().getSpeciesDAO();
    }

    public RaceDAO getRaceDAO() {
        return getDAOFactory().getRaceDAO();
    }

    public ColorDAO getColorDAO() {
        return getDAOFactory().getColorDAO();
    }

    public ContactRequestDAO getContactRequestDAO() {
        return getDAOFactory().getContactRequestDAO();
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

    public List<Race> getRacesBySpecies(Species species) {
        return getRaceDAO().findBySpecies(species);
    }

    public List<Race> getRacesBySpecies(Long id) {
        Species species = getSpecies(id);
        if (species == null) {
            return new ArrayList<Race>();
        }
        return getRacesBySpecies(species);
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

    public List<ContactRequest> getContactRequestsByAnimal(Animal animal) {
        return getContactRequestDAO().findByAnimal(animal);
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
