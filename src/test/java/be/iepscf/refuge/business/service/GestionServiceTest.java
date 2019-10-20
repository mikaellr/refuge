package be.iepscf.refuge.business.service;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.ContactRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionServiceTest {

    protected GestionService getGestionService() {
        return ServiceFactory.getGestionService();
    }

    @Test
    void getContactRequestsByAnimal() {
        Long idBubulle = 36L;
        Animal animal = getGestionService().getAnimal(idBubulle);
        List<ContactRequest> items = getGestionService().getContactRequestsByAnimal(animal);
        assertTrue(items.size() > 0);
        for (ContactRequest item : items) {
            assertTrue(item instanceof ContactRequest);
        }
    }
}