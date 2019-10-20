package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.AnimalMAO;

import java.util.List;

public class WebServiceAnimalMAO extends WebServiceGenericMAO<Animal, Long> implements AnimalMAO {
    @Override
    public List<Animal> getQuery(Species species, Race race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all) {
        return null;
    }

    @Override
    public List<Animal> getQuery(Long species, Long race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all) {
        return null;
    }
}
