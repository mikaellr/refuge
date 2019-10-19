package be.iepscf.refuge.business.service;

import be.iepscf.refuge.business.businessbean.*;
import be.iepscf.refuge.business.mao.*;
import be.iepscf.refuge.business.mao.crosstierjpa.CrossTierJpaMAOFactory;
import be.iepscf.refuge.business.mao.webservice.WebServiceMAOFactory;

import java.util.List;


/***
 * @TODO supprimer le couplage à la couche persistence (BeanService)
 */
public class ModelService {

    private MAOFactory crossTierJpaMAOFactory;
    private MAOFactory webserviceMAOFactory;



    /* "Model Access Objects" Factories : */



    public MAOFactory getMAOFactory() {
        return getCrossTierJpaMAOFactory();
    }

    public MAOFactory getCrossTierJpaMAOFactory() {
        if (crossTierJpaMAOFactory == null) {
            crossTierJpaMAOFactory = new CrossTierJpaMAOFactory();
        }
        return crossTierJpaMAOFactory;
    }

    public MAOFactory getWebserviceMAOFactory() {
        if (webserviceMAOFactory == null) {
            webserviceMAOFactory = new WebServiceMAOFactory();
        }
        return webserviceMAOFactory;
    }



    /* "Model Access Objects" : */



    public UserMAO getUserMao() {
        return getMAOFactory().getUserMAO();
    }

    public RoleMAO getRoleMAO() {
        return getMAOFactory().getRoleMAO();
    }

    public AnimalMAO getAnimalMAO() {
        return getMAOFactory().getAnimalMAO();
    }

    public SpeciesMAO getSpeciesMAO() {
        return getMAOFactory().getSpeciesMAO();
    }

    public RaceMAO getRaceMAO() {
        return getMAOFactory().getRaceMAO();
    }

    public ColorMAO getColorMAO() {
        return getMAOFactory().getColorMAO();
    }

    public ContactRequestMAO getContactRequestMAO() {
        return getMAOFactory().getContactRequestMAO();
    }






    /* User : */

    public User getUserByEmail(String email) {
        return  getUserMao().getByEmail(email);
    }

    public User getUser(Long id) {
        return getUserMao().get(id);
    }

    public List<User> getUsers() {
        return getUserMao().get();
    }

    public long saveUser(User user) {
        return getUserMao().save(user);
    }

    public long updateUser(User user) {
        return getUserMao().update(user);
    }

    public long deleteUser(User user) {
        return getUserMao().delete(user);
    }





    /* Role : */

    public Role getRole(Long id) {
        return getRoleMAO().get(id);
    }

    public List<Role> getRoles() {
        return getRoleMAO().get();
    }





    /* Species : */

    public Species getSpecies(Long id) {
        return getSpeciesMAO().get(id);
    }

    public Species getSpecies(String name) {
        return getSpeciesMAO().get(name);
    }

    public List<Species> getSpecies() {
        return getSpeciesMAO().get();
    }





    /* Race : */

    public Race getRace(Long id) {
        return getRaceMAO().get(id);
    }

    public List<Race> getRaces() {
        return getRaceMAO().get();
    }

    public List<Race> getRacesBySpecies(Species species)  {
        return getRaceMAO().getBySpecies(species);
    }

    public List<Race> getRacesBySpecies(Long id)  {
        return getRaceMAO().getBySpecies(id);
    }





    /* Color : */

    public Color getColor(Long id) {
        return getColorMAO().get(id);
    }

    public Color getColor(String name) {
        return getColorMAO().get(name);
    }

    public List<Color> getColors() {
        return getColorMAO().get();
    }





    /* Animal : */

    public Animal getAnimal(Long id) {
        return getAnimalMAO().get(id);
    }

    public List<Animal> getAnimals() {
        return getAnimalMAO().get();
    }

    public List<Animal> getAnimalsQuery(Species species, Race race, long offset, long limit, boolean last, boolean adoptable, boolean all) {
        return getAnimalMAO().getQuery(species, race, offset, limit, last, adoptable, all);
    }

    public List<Animal> getAnimalsQuery(long species, long race, long offset, long limit, boolean last, boolean adoptable, boolean all) {
        return getAnimalMAO().getQuery(species, race, offset, limit, last, adoptable, all);
    }



    /* ContactRequest : */

    public ContactRequest getContactRequest(Long id) {
        return getContactRequestMAO().get(id);
    }

    public List<ContactRequest> getContactRequests() {
        return getContactRequestMAO().get();
    }

    public long saveContactRequest(ContactRequest contactRequest) {
        return getContactRequestMAO().save(contactRequest);
    }

    public long updateContactRequest(ContactRequest contactRequest) {
        return getContactRequestMAO().update(contactRequest);
    }

}
