package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.SpeciesMAO;

import java.util.List;

public class CrossTierJpaSpeciesMAO extends CrossTierJpaGenericMAO<Species, Long> implements SpeciesMAO {


    @Override
    public Species get(Long id) {
        return conv(getBeanService().getSpecies(id));
    }

    @Override
    public Species get(String name) {
        return conv(getBeanService().getSpeciesByName(name));
    }

    @Override
    public List<Species> get() {
        return convSpecies(getBeanService().getSpecies());
    }

    @Override
    public long save(Species entity) {
        return getBeanService().saveSpecies(conv(entity));
    }

    @Override
    public long update(Species entity) {
        return getBeanService().updateSpecies((conv(entity)));
    }

    @Override
    public long delete(Species entity) {
        return getBeanService().deleteSpecies(conv(entity));
    }

}
