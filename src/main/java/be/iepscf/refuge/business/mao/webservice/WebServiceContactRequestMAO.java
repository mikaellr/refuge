package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.business.mao.ContactRequestMAO;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class WebServiceContactRequestMAO extends WebServiceGenericMAO<ContactRequest, Long> implements ContactRequestMAO {

    public WebServiceContactRequestMAO() {
        entityUrl = "contact-requests";
    }

    @Override
    public List<ContactRequest> get() {
        Response response = getEntityWebTarget().request(MediaType.APPLICATION_JSON).get();
        List<ContactRequest> items = response.readEntity(new GenericType<List<ContactRequest>>() {});
        return items;
    }

    @Override
    public List<ContactRequest> getByAnimal(Animal animal) {
        if (animal == null)  {
            return null;
        }
        return getByAnimal(animal.getId());
    }

    @Override
    public List<ContactRequest> getByAnimal(Long id) {
        Response response = getWebTarget().path("animals").path(id.toString()).path("contact-requests").request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
            List<ContactRequest> items = response.readEntity(new GenericType<List<ContactRequest>>() {});
            return items;
        }
         return null;
    }

    @Override
    public long update(ContactRequest item) {
        return update(item, item.getId().toString());
    }

}
