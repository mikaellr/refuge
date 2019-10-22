package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceRaceMAOTest {

    @Test
    void get() {
        Long id = 2L;
        WebServiceRaceMAO mao = new WebServiceRaceMAO();
        Race item = mao.get(id);
        assertTrue(item instanceof Race);
        assertEquals(item.getId(), id);
    }

    @Test
    void getAll() {
        WebServiceRaceMAO mao = new WebServiceRaceMAO();
        List<Race> items = mao.get();
        assertTrue(items.size() > 0);
        for (Race item : items) {
            assertTrue(item instanceof Race);
        }
    }

    @Test
    void getBySpecies() {
        Species species = (new WebServiceSpeciesMAO()).get(3L);
        WebServiceRaceMAO mao = new WebServiceRaceMAO();
        List<Race> items = mao.getBySpecies(species);
        assertTrue(items.size() > 0);
        for (Race item : items) {
            assertTrue(item instanceof Race);
            assertEquals(item.getSpecies().getId(), species.getId());
        }
    }

    @Test
    void getBySpeciesId() {
        Long idSpecies = 2L;
        WebServiceRaceMAO mao = new WebServiceRaceMAO();
        List<Race> items = mao.getBySpecies(idSpecies);
        assertTrue(items.size() > 0);
        for (Race item : items) {
            assertTrue(item instanceof Race);
            assertEquals(item.getSpecies().getId(), idSpecies);
        }
    }

}