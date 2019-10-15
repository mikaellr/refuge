package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.entitybean.Role;
import be.iepscf.refuge.persistence.entitybean.User;
import be.iepscf.refuge.persistence.service.BeanService;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcUserDAOTest {

    BeanService beanService = new BeanService();
    Role employeRole;

    protected Role getEmployeRole() {
        if (employeRole == null) {
            return beanService.getEmployeRole();
        }
        return employeRole;
    }

    protected User createUser() {
        String firstName = "René";
        String lastName = "Char";
        String uuid = java.util.UUID.randomUUID().toString();
        String email = String.format("%s.%s.%s@gmail.com", firstName, lastName, uuid);
        User item = new User(null, firstName, lastName, email, null, uuid, uuid, true, getEmployeRole());
        return item;
    }

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