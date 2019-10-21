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
    public List<Animal> getQuery(Species species, Race race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all) {
        Long speciesId = species != null ? species.getId() : null;
        Long raceId = race != null ? race.getId() : null;
        return getQuery(speciesId, raceId, offset, limit, last, adoptable, all);
    }

    @Override
    public List<Animal> getQuery(Long species, Long race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all) {
        return convAnimals(getBeanService().getAnimalsByParameters(species, race, adoptable, all, last, limit, offset));
    }

}
