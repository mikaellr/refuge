package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.entitybean.Color;
import be.iepscf.refuge.persistence.entitybean.Role;
import be.iepscf.refuge.persistence.entitybean.User;
import be.iepscf.refuge.persistence.service.BeanService;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcColorDAOTest {



    @Test
    void testFind() {
        Long id = 1L;
        JdbcColorDAO dao = new JdbcColorDAO();
        Color color  = dao.find(id);
        assertTrue(color instanceof Color);
        assertEquals(id, color.getId());
    }

    @Test
    void testFindAll() {
        JdbcColorDAO dao = new JdbcColorDAO();
        List<Color> colors  = dao.findAll();
        assertTrue(colors.size() > 0);
        for (Color item : colors) {
            assertTrue(item instanceof Color);
        }
    }

    @Test
    void testFindByName() {
        String name = "noire";
        JdbcColorDAO dao = new JdbcColorDAO();
        Color color  = dao.findByName(name);
        assertTrue(color instanceof Color);
        assertEquals(name, color.getName());
    }

}