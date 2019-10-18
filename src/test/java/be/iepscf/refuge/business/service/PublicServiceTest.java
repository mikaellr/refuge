package be.iepscf.refuge.business.service;

import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.persistence.dao.voldemort.util.EntityManagerHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicServiceTest {

    @Test
    void addContactRequest() {
        PublicService service = new PublicService();
        long idMaineCoon = 21;
        String firstName = "Jean-Louis";
        String lastName = "Mounier";
        String email = "jeanlouismounier@laposte.net";
        String phone = "042/457812";
        String message = "Joli chat, il est pour moi.";
        ContactRequest item = service.addContactRequest(firstName, lastName, email, phone, message , idMaineCoon);
        assertNotNull(item);
        assertTrue(item instanceof ContactRequest);
        assertEquals(item.getFirstName(), firstName);
        assertEquals(item.getLastName(), lastName);
        assertEquals(item.getEmail(), email);
        assertEquals(item.getPhone(), phone);
        assertEquals(item.getMessage(), message);
        assertEquals(item.getAnimal().getId(), idMaineCoon);
    }

}