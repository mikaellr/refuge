package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.BaseTest;
import be.iepscf.refuge.persistence.entitybean.Role;
import be.iepscf.refuge.persistence.entitybean.User;
import be.iepscf.refuge.persistence.service.BeanService;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcUserDAOTest extends BaseTest {


    @Test
    void save() {
        User item = createUser();
        JdbcUserDAO dao = new JdbcUserDAO();
        System.out.println(item);
        long lastInsertId = dao.save(item);
        assertTrue(lastInsertId > 0 );
        assertEquals(item.getId(), lastInsertId);
    }

    @Test
    void update() {
        Long id = 1L;
        JdbcUserDAO dao = new JdbcUserDAO();
        User item = dao.find(id);
        String phone = ("" + new Date());
        item.setPhone(phone);
        long affectedRows = dao.update(item);
        User bis = dao.find(id);
        assertTrue(affectedRows > 0);
        assertEquals(item.getPhone(), phone);
        assertEquals(bis.getPhone(), phone);
    }

    @Test
    void delete() {
    }

    @Test
    void find() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByEmail() {
    }
}