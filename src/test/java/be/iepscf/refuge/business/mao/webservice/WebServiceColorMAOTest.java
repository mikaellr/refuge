package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceColorMAOTest {

    @Test
    void get() {
        Long id = 2L;
        WebServiceColorMAO mao = new WebServiceColorMAO();
        Color item = mao.get(id);
        assertTrue(item instanceof Color);
        assertEquals(item.getId(), id);
    }

    @Test
    void getAll() {
        WebServiceColorMAO mao = new WebServiceColorMAO();
        List<Color> items = mao.get();
        assertTrue(items.size() > 0);
        for (Color item : items) {
            assertTrue(item instanceof Color);
        }
    }

    @Test
    void getByName() {
        String name = "noire";
        WebServiceColorMAO mao = new WebServiceColorMAO();
        Color item = mao.get(name);
        assertTrue(item instanceof Color);
        assertEquals(item.getName(), name);
    }

}