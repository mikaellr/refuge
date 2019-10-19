package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.RaceMAO;

import java.util.ArrayList;
import java.util.List;

public class CrossTierJpaRaceMAO extends CrossTierJpaGenericMAO<Race, Long> implements RaceMAO {

    @Override
    public Race get(Long id) {
        return conv(getBeanService().getRace(id));
    }

    @Override
    public List<Race> get() {
        return convRaces(getBeanService().getRaces());
    }

    @Override
    public long save(Race entity) {
        return getBeanService().saveRace(conv(entity));
    }

    @Override
    public long update(Race entity) {
        return getBeanService().updateRace(conv(entity));
    }

    @Override
    public long delete(Race entity) {
        return getBeanService().deleteRace(conv(entity));
    }

    @Override
    public List<Race> getBySpecies(Species species) {
        // following method doesn't exist yet :
        // return getBeanService().getRacesBySpecies(species);
        List<Race> filtered = new ArrayList<Race>();
        List<Race> all = get();
        for (Race race : all) {
            if (race.getSpecies().getId() == species.getId()) {
                filtered.add(race);
            }
        }
        return filtered;
    }

    @Override
    public List<Race> getBySpecies(Long id) {
        // following method doesn't exist yet :
        // return getBeanService().getRacesBySpecies(id);
        List<Race> filtered = new ArrayList<Race>();
        List<Race> all = get();
        for (Race race : all) {
            if (race.getSpecies().getId() == id) {
                filtered.add(race);
            }
        }
        return filtered;
    }
}
