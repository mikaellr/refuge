package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Role;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceRoleMAOTest {

    @Test
    void get() {
        Long id = 1L;
        WebServiceRoleMAO mao = new WebServiceRoleMAO();
        Role item = mao.get(id);
        assertTrue(item instanceof Role);
        assertEquals(item.getId(), id);
    }

    @Test
    void testGet() {
        WebServiceRoleMAO mao = new WebServiceRoleMAO();
        List<Role> items = mao.get();
        assertTrue(items.size() > 0);
        for (Role item : items) {
            assertTrue(item instanceof Role);
        }
    }

}