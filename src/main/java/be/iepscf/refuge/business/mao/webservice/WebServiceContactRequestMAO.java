package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.business.mao.ContactRequestMAO;

import java.util.List;

public class WebServiceContactRequestMAO extends WebServiceGenericMAO<ContactRequest, Long> implements ContactRequestMAO {
    @Override
    public List<ContactRequest> getByAnimal(Animal animal) {
        return null;
    }
}
