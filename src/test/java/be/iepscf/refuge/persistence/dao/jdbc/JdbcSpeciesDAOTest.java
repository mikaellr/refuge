package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.BaseTest;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSpeciesDAOTest extends BaseTest {

    @Test
    void testFind() {
        Long id = 1L;
        JdbcSpeciesDAO dao = new JdbcSpeciesDAO();
        Species Species  = dao.find(id);
        assertTrue(Species instanceof Species);
        assertEquals(id, Species.getId());
    }

    @Test
    void testFindAll() {
        JdbcSpeciesDAO dao = new JdbcSpeciesDAO();
        List<Species> items  = dao.findAll();
        assertNotNull(items);
        assertTrue(items.size() > 0);
        for (Species item : items) {
            assertTrue(item instanceof Species);
        }
    }

}