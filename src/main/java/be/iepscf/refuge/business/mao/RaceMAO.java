package be.iepscf.refuge.business.mao;

import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;

import java.util.List;

public interface RaceMAO extends GenericMAO<Race, Long> {

    public List<Race> getBySpecies(Species species);

    public List<Race> getBySpecies(Long id);

}
