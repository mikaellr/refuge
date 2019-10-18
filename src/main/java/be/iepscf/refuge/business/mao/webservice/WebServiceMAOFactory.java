package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.mao.*;

import java.util.List;

public class WebServiceMAOFactory extends MAOFactory {

    @Override
    public AnimalMAO getAnimalMAO() {
        return new WebServiceAnimalMAO();
    }

    @Override
    public ColorMAO getColorMAO() {
        return new WebServiceColorMAO();
    }

    @Override
    public ContactRequestMAO getContactRequestMAO() {
        return new WebServiceContactRequestMAO();
    }

    @Override
    public RaceMAO getRaceMAO() {
        return new WebServiceRaceMAO();
    }

    @Override
    public RoleMAO getRoleMAO() {
        return new WebServiceRoleMAO();
    }

    @Override
    public SpeciesMAO getSpeciesMAO() {
        return new WebServiceSpeciesMAO();
    }

    @Override
    public UserMAO getUserMAO() {
        return new WebServiceUserMAO();
    }
}
