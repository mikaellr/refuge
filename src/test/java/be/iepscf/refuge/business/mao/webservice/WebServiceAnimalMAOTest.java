package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.BaseTest;
import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Species;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceAnimalMAOTest extends BaseTest {

    protected Animal createAnimal() {
        Animal item  = new Animal();
        item.setName("Gros");
        item.setDescription("grosse bête");
        item.setBirthYear(2014);
        item.setSex('f');
        item.setAdoptable(true);
        item.setSterilized(true);
        Species cheval = (new WebServiceSpeciesMAO()).get("Cheval");
        return item;
    }


    @Test
    void get() {
        WebServiceAnimalMAO mao = new WebServiceAnimalMAO();
        Long id = 1L;
        Animal item = mao.get(id);
        assertTrue(item instanceof  Animal);
        assertEquals(item.getId(), id);
    }

    @Test
    void getAll() {
        WebServiceAnimalMAO mao = new WebServiceAnimalMAO();
        List<Animal> items = mao.get();
        assertTrue(items.size() > 0);
        for (Animal item : items) {
            assertTrue(item instanceof Animal);
        }
    }

    @Test
    void save() {
        Animal item = createAnimal();
        WebServiceAnimalMAO mao = new WebServiceAnimalMAO();
        long res = mao.save(item);
        assertTrue(res > 0);
    }

    @Test
    void update() {
        Long id = 1l;
        WebServiceAnimalMAO mao = new WebServiceAnimalMAO();
        Animal item = mao.get(id);
        String desc = "desc" + (new Date());
        item.setDescription(desc);
        long res = mao.update(item);
        assertTrue(res > 0);
    }

}