package be.iepscf.refuge.business.mao;

import be.iepscf.refuge.business.businessbean.Species;

public interface SpeciesMAO extends GenericMAO<Species, Long>{

    public Species get(String name);

}
