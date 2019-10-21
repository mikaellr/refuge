package be.iepscf.refuge;

import be.iepscf.refuge.persistence.entitybean.*;
import be.iepscf.refuge.persistence.service.BeanService;

import javax.management.MBeanServer;
import java.util.Date;

public class BaseTest {

    BeanService beanService = new BeanService();
    Role employeRole;

    protected BeanService getBeanService() {
        return beanService;
    }

    protected Role getEmployeRole() {
        if (employeRole == null) {
            return beanService.getEmployeRole();
        }
        return employeRole;
    }

    /**
     * creates random User object for use in a test
     */
    protected User createUser(String firstName, String lastName) {
        String email = firstName+"."+lastName+"." + (new Date()).getTime()  +"@gmail.com";
        User user = new User(null, firstName, lastName, email, null, "xxx", "xxx", true,
                new Role(1L, "Employé", "Les employés du refuge"));
        return user;
    }

    protected User createUser() {
        String firstName = "René";
        String lastName = "Char";
        String uuid = java.util.UUID.randomUUID().toString();
        String email = String.format("%s.%s.%s@gmail.com", firstName, lastName, uuid);
        User item = new User(null, firstName, lastName, email, null, uuid, uuid, true, getEmployeRole());
        return item;
    }





    protected Animal getAnimal(Long id) {
        return (new BeanService()).getAnimal(id);

    }

    protected Animal getAnimal() {
        return getAnimal(32L);
    }

    protected Animal getAnimalHavingContactRequests() {
        return (new BeanService()).getAnimal(32L); // bubulle
    }

    protected Species getSpeciesHavingRaces() {
        return (new BeanService()).getSpeciesByName("Chat");
    }

    protected ContactRequest createContactRequest() {
        String firstName = "Louis";
        String lastName = "Dupont";
        String uuid = java.util.UUID.randomUUID().toString();
        String email = String.format("%s.%s.%s@gmail.com", firstName, lastName, uuid);
        String phone = "081/14786x";
        String message = "je voudrais savoir un truc";
        Date date = new Date();
        boolean treated = false;
        Animal animal = getAnimal();
        ContactRequest cr = new ContactRequest(null, firstName, lastName, email, phone, message, date, treated, animal);
        return cr;
    }

}
