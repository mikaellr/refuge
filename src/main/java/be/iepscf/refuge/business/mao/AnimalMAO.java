package be.iepscf.refuge.business.mao;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;

import java.util.List;

public interface AnimalMAO extends GenericMAO<Animal, Long> {

    public List<Animal> getQuery(Species species, Race race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all);

    public List<Animal> getQuery(Long species, Long race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all);

}
