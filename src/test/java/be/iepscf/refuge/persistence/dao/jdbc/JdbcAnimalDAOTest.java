package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.BaseTest;
import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.Color;
import be.iepscf.refuge.persistence.entitybean.Species;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcAnimalDAOTest extends BaseTest {


    protected Animal createAnimal() {
        Color color = getBeanService().getColorByName("blanche");
        Species species = getBeanService().getSpeciesByName("Chat");
        Animal animal = new Animal();
        animal.setName("toto");
        animal.setDescription("toto est gentil");
        animal.setBirthYear(2009);
        animal.setSex('m');
        animal.setSterilized(true);
        animal.setAdoptable(true);
        animal.setSpecies(species);
        animal.setColor(color);
        return animal;
    }

    @Test
    void save() {
        Animal item = createAnimal();
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        long lastInsertId = dao.save(item);
        assertTrue(lastInsertId > 0 );
        assertEquals(item.getId(), lastInsertId);
    }

    @Test
    void update() {
        Long id = 33L;
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        Animal item = dao.find(id);
        String desc = ("" + new Date());
        item.setDescription(desc);
        long affectedRows = dao.update(item);
        Animal bis = dao.find(id);
        assertTrue(affectedRows > 0);
        assertEquals(item.getDescription(), desc);
        assertEquals(bis.getDescription(), desc);
    }

    @Test
    void delete() {
    }

    @Test
    void find() {
        Long id = 3L;
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        Animal item = dao.find(id);
        assertTrue(item instanceof Animal);
        assertEquals(id, item.getId());
    }

    @Test
    void findAll() {
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        List<Animal> items = dao.findAll();
        assertTrue(items.size() > 0);
        for (Animal item : items) {
            assertTrue(item instanceof Animal);
            assertTrue(item.isAdoptable());
        }
    }

    @Test
    void findBySpecies() {
        Long IdChat = 2L;
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        List<Animal> items = dao.findBySpecies(IdChat);
        assertTrue(items.size() > 0);
        for (Animal item : items) {
            assertTrue(item instanceof Animal);
            assertTrue(item.isAdoptable());
            assertEquals(item.getSpecies().getId(), IdChat);
        }
    }

    @Test
    void findByRace() {
        Long IdBergerAllemand = 24L;
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        List<Animal> items = dao.findByRace(IdBergerAllemand);
        assertTrue(items.size() > 0);
        for (Animal item : items) {
            assertTrue(item instanceof Animal);
            assertTrue(item.isAdoptable());
            assertEquals(item.getRace().getId(), IdBergerAllemand);
        }
    }

    @Test
    void findNonAdoptable() {
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        List<Animal> items = dao.findNonAdoptable();
        assertTrue(items.size() > 0);
        for (Animal item : items) {
            assertTrue(item instanceof Animal);
            assertFalse(item.isAdoptable());
        }
    }

    @Test
    void findQueryDefault() {
        Long defaultLimit = 20L;
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        List<Animal> items = dao.findMultiParameters(null, null, null, null, null, null, null);
        assertTrue(items.size() == defaultLimit);
        for (Animal item : items) {
            assertTrue(item instanceof Animal);
        }
    }

    @Test
    void findQueryBySpecies() {
        Long idChat = 2L;
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        List<Animal> items = dao.findMultiParameters(idChat, null, null, null, null, null, null);
        assertTrue(items.size() > 0);
        for (Animal item : items) {
            assertTrue(item instanceof Animal);
            assertEquals(item.getSpecies().getId(), idChat);
        }
    }

    @Test
    void findQueryBySpeciesAndRace() {
        Long idChien = 1L;
        Long idBergerAllemand = 24L;
        JdbcAnimalDAO dao = new JdbcAnimalDAO();
        List<Animal> items = dao.findMultiParameters(idChien, idBergerAllemand, null, null, null, null, null);
        assertTrue(items.size() > 0);
        for (Animal item : items) {
            assertTrue(item instanceof Animal);
            assertEquals(item.getSpecies().getId(), idChien);
            assertEquals(item.getRace().getId(), idBergerAllemand);
        }
    }

}