package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.BaseTest;
import be.iepscf.refuge.business.businessbean.Role;
import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.util.PasswordManager;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceUserMAOTest extends BaseTest {

    protected User createBusinessUser() {
        PasswordManager pm = new PasswordManager();
        Role role = (new WebServiceRoleMAO()).get(1l);
        User item = new User();
        item.setFirstName("Jean");
        item.setLastName("Louis");
        item.setEmail("jean.louis@gmail.com" + (new Date()).getTime());
        item.setRole(role);
        pm.setUserPassword(item, "Jean");
        return item;
    }

    @Test
    void getByEmail() {
        WebServiceUserMAO mao = new WebServiceUserMAO();
        String email = "laura.lorapa@gmail.com";
        User item = mao.getByEmail(email);
        assertTrue(item instanceof  User);
        assertEquals(item.getEmail(), email);
    }

    @Test
    void getByEmailNonExisting() {
        WebServiceUserMAO mao = new WebServiceUserMAO();
        String email = "laura.xywtf.lorapa@gmail.com";
        User item = mao.getByEmail(email);
        assertNull(item);
    }

    @Test
    void get() {
        WebServiceUserMAO mao = new WebServiceUserMAO();
        Long id = 1L;
        User item = mao.get(id);
        assertTrue(item instanceof  User);
        assertEquals(item.getId(), id);
    }

    @Test
    void testGets() {
        WebServiceUserMAO mao = new WebServiceUserMAO();
        List<User> items = mao.get();
        assertTrue(items.size() > 0);
        for (User item : items) {
            assertTrue(item instanceof User);
        }
    }

    @Test
    void save() {
        User item = createBusinessUser();
        WebServiceUserMAO mao = new WebServiceUserMAO();
        long id = mao.save(item);
        assertTrue(id > 0);
        User item2 = mao.get(id);
        assertTrue(item2 instanceof User);
        assertEquals(item.getEmail(), item2.getEmail());
    }

    @Test
    void update() {
        Long id = 1l;
        WebServiceUserMAO mao = new WebServiceUserMAO();
        User item = mao.get(id);
        String newPhone = "081/" + (new Date());
        item.setPhone(newPhone);
        mao.update(item);
        User item2 = mao.get(id);
        assertEquals(newPhone, item2.getPhone());
    }

    @Test
    void delete() {
        User item = createBusinessUser();
        WebServiceUserMAO mao = new WebServiceUserMAO();
        Long idd = mao.save(item);
        Long id = item.getId();
        debug("test user delete save id:" +id);
        debug("test user delete save idd:" +idd);
        assertTrue(id != null);
        mao.delete(item);
        User item2 = mao.get(id);
        assertNull(item2);
    }
}