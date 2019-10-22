package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Species;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceSpeciesMAOTest {

    @Test
    void get() {
        Long id = 2L;
        WebServiceSpeciesMAO mao = new WebServiceSpeciesMAO();
        Species item = mao.get(id);
        assertTrue(item instanceof Species);
        assertEquals(item.getId(), id);
    }

    @Test
    void getAll() {
        WebServiceSpeciesMAO mao = new WebServiceSpeciesMAO();
        List<Species> items = mao.get();
        assertTrue(items.size() > 0);
        for (Species item : items) {
            assertTrue(item instanceof Species);
        }
    }

    @Test
    void getByName() {
        String name = "Chien";
        WebServiceSpeciesMAO mao = new WebServiceSpeciesMAO();
        Species item = mao.get(name);
        assertTrue(item instanceof Species);
        assertEquals(item.getName(), name);
    }

}
