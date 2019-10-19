package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.entitybean.Race;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcRaceDAOTest {



    @Test
    void testFind() {
        Long id = 1L;
        JdbcRaceDAO dao = new JdbcRaceDAO();
        Race Race  = dao.find(id);
        assertTrue(Race instanceof Race);
        assertEquals(id, Race.getId());
    }

    @Test
    void testFindAll() {
        JdbcRaceDAO dao = new JdbcRaceDAO();
        List<Race> Races  = dao.findAll();
        assertTrue(Races.size() > 0);
        for (Race item : Races) {
            assertTrue(item instanceof Race);
        }
    }

}