package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.BaseTest;
import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcRaceDAOTest extends BaseTest {

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
        List<Race> races  = dao.findAll();
        assertTrue(races.size() > 0);
        for (Race item : races) {
            assertTrue(item instanceof Race);
        }
    }

    @Test
    void testFindBySpecies() {
        Long idChat = 2L;
        Species chat = (new JdbcSpeciesDAO()).find(idChat);
        JdbcRaceDAO dao = new JdbcRaceDAO();
        List<Race> races  = dao.findBySpecies(chat);
        assertTrue(races.size() > 0);
        for (Race item : races) {
            assertEquals(item.getSpecies().getId(), chat.getId());
        }
    }

}