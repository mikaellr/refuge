package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.entitybean.Role;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcRoleDAOTest {

    @Test
    void testFind() {
        Long id = 1L;
        JdbcRoleDAO dao = new JdbcRoleDAO();
        Role Role  = dao.find(id);
        assertTrue(Role instanceof Role);
        assertEquals(id, Role.getId());
    }

    @Test
    void testFindAll() {
        JdbcRoleDAO dao = new JdbcRoleDAO();
        List<Role> Roles  = dao.findAll();
        assertTrue(Roles.size() > 0);
        for (Role item : Roles) {
            assertTrue(item instanceof Role);
        }
    }

}