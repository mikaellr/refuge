package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.business.businessbean.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceContactRequestMAOTest {

    @Test
    void get() {
        WebServiceContactRequestMAO mao = new WebServiceContactRequestMAO();
        Long id = 1L;
        ContactRequest item = mao.get(id);
        assertTrue(item instanceof  ContactRequest);
        assertEquals(item.getId(), id);
    }

    @Test
    void getAll() {
        WebServiceContactRequestMAO mao = new WebServiceContactRequestMAO();
        List<ContactRequest> items = mao.get();
        assertNotNull(items);
        assertTrue(items.size() > 0);
        for (ContactRequest item : items) {
            assertTrue(item instanceof ContactRequest);
        }
    }

    @Test
    void getByAnimal() {
        Long idBubulle = 32L;
        Animal animal = (new WebServiceAnimalMAO()).get(idBubulle);
        assertNotNull(animal);
        WebServiceContactRequestMAO mao = new WebServiceContactRequestMAO();
        List<ContactRequest> items = mao.getByAnimal(animal);
        assertNotNull(items);
        assertTrue(items.size() > 0);
        for (ContactRequest item : items) {
            assertTrue(item instanceof ContactRequest);
            assertEquals(item.getAnimal().getId(), idBubulle);
        }
    }
    @Test
    void save() {
        ContactRequest item = createContactRequest();
        WebServiceContactRequestMAO mao = new WebServiceContactRequestMAO();
        long res = mao.save(item);
        assertTrue(res > 0);
    }

    @Test
    void update() {
        WebServiceContactRequestMAO mao = new WebServiceContactRequestMAO();
        ContactRequest item = mao.get(1L);
        String email = "" + (new Date());;
        item.setEmail(email);
        long res = mao.update(item);
        assertTrue(res > 0);
    }

    protected ContactRequest createContactRequest() {
        Animal animal = (new WebServiceAnimalMAO()).get(1L);
        ContactRequest cr = new ContactRequest();
        cr.setFirstName("Mélanie");
        cr.setLastName("Boucher");
        cr.setEmail("melanie.boucher@gmail.com");
        cr.setPhone("081...");
        cr.setMessage("Et blah blah");
        cr.setDate(new Date());
        cr.setTreated(false);
        cr.setAnimal(animal);
        return cr;
    }

}