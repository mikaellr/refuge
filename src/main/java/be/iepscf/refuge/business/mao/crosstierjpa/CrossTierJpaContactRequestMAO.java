package be.iepscf.refuge.business.mao.crosstierjpa;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.business.mao.ContactRequestMAO;

import java.util.List;

public class CrossTierJpaContactRequestMAO extends CrossTierJpaGenericMAO<ContactRequest, Long> implements ContactRequestMAO {

    @Override
    public ContactRequest get(Long id) {
        return conv(getBeanService().getContactRequest(id));
    }

    @Override
    public List<ContactRequest> get() {
        return convContactRequests(getBeanService().getContactRequests());
    }

    @Override
    public long save(ContactRequest entity) {
        return getBeanService().saveContactRequest(conv(entity));
    }

    @Override
    public long update(ContactRequest entity) {
        return getBeanService().updateContactRequest(conv(entity));
    }

    @Override
    public long delete(ContactRequest entity) {
        return getBeanService().deleteContactRequest(conv(entity));
    }

    @Override
    public List<ContactRequest> getByAnimal(Animal animal) {
        return convContactRequests(getBeanService().getContactRequestsByAnimal(conv(animal)));
    }

    @Override
    public List<ContactRequest> getByAnimal(Long id) {
        return null;
    }
}
