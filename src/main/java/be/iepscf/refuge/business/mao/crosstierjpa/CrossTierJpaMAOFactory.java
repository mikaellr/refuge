package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.mao.*;

public class CrossTierJpaMAOFactory extends MAOFactory {

    @Override
    public AnimalMAO getAnimalMAO() {
        return new CrossTierJpaAnimalMAO();
    }

    @Override
    public ColorMAO getColorMAO() {
        return new CrossTierJpaColorMAO();
    }

    @Override
    public ContactRequestMAO getContactRequestMAO() {
        return new CrossTierJpaContactRequestMAO();
    }

    @Override
    public RaceMAO getRaceMAO() {
        return new CrossTierJpaRaceMAO();
    }

    @Override
    public RoleMAO getRoleMAO() {
        return new CrossTierJpaRoleMAO();
    }

    @Override
    public SpeciesMAO getSpeciesMAO() {
        return new CrossTierJpaSpeciesMAO();
    }

    @Override
    public UserMAO getUserMAO() {
        return new CrossTierJpaUserMAO();
    }
}
