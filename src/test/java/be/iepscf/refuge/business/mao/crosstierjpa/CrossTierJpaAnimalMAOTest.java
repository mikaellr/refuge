package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.businessbean.Animal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CrossTierJpaAnimalMAOTest {

    @Test
    void testGetQuery() {
        Long idChien = 1L;
        Long idBergerAllemand = 24L;
        CrossTierJpaAnimalMAO mao = new CrossTierJpaAnimalMAO();
        List<Animal> items = mao.getQuery(idChien,idBergerAllemand, 0L, 20L, true, true, false);
        assertTrue(items.size() > 0);
        for (Animal item : items) {
            System.out.println(item);
            assertEquals(item.getSpecies().getId(), idChien);
            assertEquals(item.getRace().getId(), idBergerAllemand);
        }
    }

}