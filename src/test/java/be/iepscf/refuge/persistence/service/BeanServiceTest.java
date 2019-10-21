package be.iepscf.refuge.persistence.service;

import be.iepscf.refuge.BaseTest;
import be.iepscf.refuge.persistence.dao.*;
import be.iepscf.refuge.persistence.entitybean.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeanServiceTest extends BaseTest {

    BeanService service = new BeanService();

    /* DAOs : */
    @Test
    public void testGetUserDAO() {
        UserDAO dao = service.getUserDAO();
        assert(dao instanceof  UserDAO);
    }

    @Test
    public void testGetRoleDAO() {
        RoleDAO dao = service.getRoleDAO();
        assert(dao instanceof  RoleDAO);
    }

    @Test
    public void testGetAnimalDAO() {
        AnimalDAO dao = service.getAnimalDAO();
        assert(dao instanceof  AnimalDAO);
    }

    @Test
    public void testGetSpeciesDAO() {
        SpeciesDAO dao = service.getSpeciesDAO();
        assert(dao instanceof  SpeciesDAO);
    }
    
    @Test
    public void testGetRaceDAO() {
        RaceDAO dao = service.getRaceDAO();
        assert(dao instanceof  RaceDAO);
    }
    
    @Test
    public void testGetColorDAO() {
        ColorDAO dao = service.getColorDAO();
        assert(dao instanceof  ColorDAO);
    }
    
    @Test
    public void testGetContactRequestDAO() {
        ContactRequestDAO dao = service.getContactRequestDAO();
        assert(dao instanceof  ContactRequestDAO);
    }
    
    

    /* User : */

    @Test
    public void testGetUser() {
        User user = service.getUser(1L);
        assertTrue((user instanceof User));
        assertTrue((user.getRole() instanceof Role));
    }

    @Test
    public void testGetUserByEmail() {
        String validEmail = "sarah.cuspote@gmail.com";
        User user = service.getUserByEmail(validEmail);
        assertTrue((user instanceof User));
        assertTrue((user.getRole() instanceof Role));
        assertEquals(user.getEmail(), validEmail);
    }

    @Test
    public void testGetUsers() {
        List<User> users = service.getUsers();
        assertTrue(users.size() > 0);
        for (User item : users) {
            assertTrue((item instanceof User));
        }
    }

    @Test
    public void testSaveUser() {
        User user = createUser("Antony", "KJK");
        long lastInsertId = service.saveUser(user);
        assertTrue(lastInsertId > 0);
        assertEquals(lastInsertId, user.getId());
        User user2 = service.getUser(lastInsertId);
        assertTrue(user2 instanceof User);
    }

    @Test
    public  void testUpdateUser() {
        String newName = (new Date()).toString();
        User user = service.getUser(4L);
        user.setLastName(newName);
        long aff = service.updateUser(user);
        assertTrue(aff > 0);
        assertEquals(1, aff);
        User user2 = service.getUser(4L);
        assertEquals(newName, user2.getLastName());
    }

    @Test
    void testDeleteUser() {
        User user = createUser("Antony", "KJK");
        long lastInsertId = service.saveUser(user);
        User user2 = service.getUser(lastInsertId);
        long affectedRows = service.deleteUser(user2);
        assertEquals(1, affectedRows);
        User user3 = service.getUser(lastInsertId);
        assertNull(user3);
    }



    /* Species : */

    @Test
    void getSpecies() {
        Long id = 1L;
        Species item = service.getSpecies(id);
        assertTrue((item instanceof Species));
        assertEquals(item.getId(), id);
    }

    @Test
    void getSpeciesByName() {
        String name = "Chat";
        Species item = service.getSpeciesByName(name);
        assertTrue((item instanceof Species));
        assertEquals(item.getName(), name);
    }

    @Test
    void testGetSpecies() {
        List<Species> items = service.getSpecies();
        assertTrue(items.size() > 0);
        for (Species item : items) {
            assertTrue((item instanceof Species));
        }
    }





    /* Race : */

    @Test
    void testGetRacesBySpecies() {
        Species species = getSpeciesHavingRaces();
        List<Race> items = service.getRacesBySpecies(species);
        assertTrue(items.size() > 0);
        for (Race item : items) {
            assertTrue((item instanceof Race));
            assertEquals(item.getSpecies().getId(), species.getId());
        }
    }



    /* Color : */

    @Test
    void testGetColors() {
        List<Color> items = service.getColors();
        assertTrue(items.size() > 0);
        for (Color item : items) {
            assertTrue((item instanceof Color));
        }
    }




    /* ContactRequest : */

    @Test
    void testGetContactRequest() {
        Long id = 1L;
        ContactRequest item = service.getContactRequest(id);
        assertTrue(item instanceof ContactRequest);
        assertEquals(id, item.getId());
    }

    @Test
    void testGetContactRequests() {
        List<ContactRequest> items = service.getContactRequests();
        assertTrue(items.size() > 0);
        for (ContactRequest item : items) {
            assertTrue(item instanceof ContactRequest);
        }
    }

    @Test
    void testGetContactRequestsByAnimal() {
        Animal animal = getAnimalHavingContactRequests();
        List<ContactRequest> items = service.getContactRequestsByAnimal(animal);
        assertTrue(items.size() > 0);
        for (ContactRequest item : items) {
            assertTrue(item instanceof ContactRequest);
            assertEquals(animal.getId(), item.getAnimal().getId());
        }
    }

}
