package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.AnimalMAO;

import java.util.ArrayList;
import java.util.List;

public class CrossTierJpaAnimalMAO extends CrossTierJpaGenericMAO<Animal, Long> implements AnimalMAO {

    @Override
    public Animal get(Long id) {
        return conv(getBeanService().getAnimal(id));
    }

    @Override
    public List<Animal> get() {
        return convAnimals(getBeanService().getAnimals());
    }

    @Override
    public long save(Animal entity) {
        return getBeanService().saveAnimal(conv(entity));
    }

    @Override
    public long update(Animal entity) {
        return getBeanService().updateAnimal(conv(entity));
    }

    @Override
    public long delete(Animal entity) {
        return getBeanService().deleteAnimal(conv(entity));
    }

    @Override
    public List<Animal> getQuery(Species species, Race race, long offset, long limit, boolean last, boolean adoptable, boolean all) {
        return getQuery(species.getId(), race.getId(), offset, limit, last, adoptable, all);
    }

    @Override
    public List<Animal> getQuery(Long species, Long race, long offset, long limit, boolean last, boolean adoptable, boolean all) {
        List<Animal> animals = get();
        List<Animal> filtered = new ArrayList<Animal>();
        for (Animal item : animals)  {
            if (species != null && item.getSpecies().getId() != species) {
                continue;
            }
            if (race != null && (item.getRace() == null || item.getRace().getId() != race)) {
                continue;
            }
            filtered.add(item);
        }
        return filtered;
    }

}
