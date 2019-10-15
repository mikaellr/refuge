package be.iepscf.refuge.persistence.service;

import be.iepscf.refuge.persistence.entitybean.Role;
import be.iepscf.refuge.persistence.entitybean.Species;
import be.iepscf.refuge.persistence.entitybean.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeanServiceTest {

    BeanService service = new BeanService();

    /**
     * creates random User object for use in a test
     */
    protected User createUser(String firstName, String lastName) {
        String email = firstName+"."+lastName+"." + (new Date()).getTime()  +"@gmail.com";
        User user = new User(null, firstName, lastName, email, null, "xxx", "xxx", true,
                new Role(1L, "Employé", "Les employés du refuge"));
        return user;
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
    void deleteUser() {
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

}
