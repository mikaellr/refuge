package be.iepscf.refuge.business.mao;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.ContactRequest;

import java.util.List;

public interface ContactRequestMAO extends GenericMAO<ContactRequest, Long>{

    public List<ContactRequest> getByAnimal(Animal animal);

}
