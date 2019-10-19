package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.RaceMAO;

import java.util.List;

public class WebServiceRaceMAO extends WebServiceGenericMAO<Race, Long> implements RaceMAO {
    @Override
    public List<Race> getBySpecies(Species species) {
        return null;
    }

    @Override
    public List<Race> getBySpecies(Long id) {
        return null;
    }
}
