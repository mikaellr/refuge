package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.AnimalMAO;

import java.util.List;

public class WebServiceAnimalMAO extends WebServiceGenericMAO<Animal, Long> implements AnimalMAO {
    @Override
    public List<Animal> getQuery(Species species, Race race, long offset, long limit, boolean last, boolean adoptable, boolean all) {
        return null;
    }

    @Override
    public List<Animal> getQuery(Long species, Long race, long offset, long limit, boolean last, boolean adoptable, boolean all) {
        return null;
    }
}
