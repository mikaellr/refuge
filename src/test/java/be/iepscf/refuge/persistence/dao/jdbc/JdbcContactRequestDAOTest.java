package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.BaseTest;
import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.ContactRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcContactRequestDAOTest extends BaseTest {

    @Test
    void save() {
        ContactRequest item = createContactRequest();
        JdbcContactRequestDAO dao = new JdbcContactRequestDAO();
        long lastInsertId = dao.save(item);
        assertTrue(lastInsertId > 0 );
        assertEquals(item.getId(), lastInsertId);
    }

    @Test
    void update() {
        Long id = 1L;
        JdbcContactRequestDAO dao = new JdbcContactRequestDAO();
        ContactRequest item = dao.find(id);
        String phone = ("" + new Date());
        item.setPhone(phone);
        long affectedRows = dao.update(item);
        ContactRequest bis = dao.find(id);
        assertTrue(affectedRows > 0);
        assertEquals(item.getPhone(), phone);
        assertEquals(bis.getPhone(), phone);
    }

    @Test
    void find() {
        Long id = 1L;
        JdbcContactRequestDAO dao = new JdbcContactRequestDAO();
        ContactRequest item = dao.find(id);
        assertTrue(item instanceof ContactRequest);
        assertEquals(item.getId(), id);
    }

    @Test
    void findAll() {
        JdbcContactRequestDAO dao = new JdbcContactRequestDAO();
        List<ContactRequest> items = dao.findAll();
        assertTrue(items.size() > 0);
        for (ContactRequest item : items) {
            assertTrue(item instanceof ContactRequest);
        }
    }

    @Test
    void findByAnimal() {
        Animal animal = getAnimal();
        JdbcContactRequestDAO dao = new JdbcContactRequestDAO();
        List<ContactRequest> items = dao.findByAnimal(animal);
        assertTrue(items.size() > 0);
        for (ContactRequest item : items) {
            assertTrue(item instanceof ContactRequest);
            assertEquals(item.getAnimal().getId(), animal.getId());
        }
    }

}