package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.SpeciesMAO;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class WebServiceSpeciesMAO extends WebServiceGenericMAO<Species, Long> implements SpeciesMAO {

    public WebServiceSpeciesMAO() {
        entityUrl = "species";
    }

    @Override
    public List<Species> get() {
        Response response =  getEntityWebTarget().request(MediaType.APPLICATION_JSON).get();
        List<Species> items = response.readEntity(new GenericType<List<Species>>() {});
        return items;
    }

    @Override
    public Species get(String name) {
        Species i = getBy("name", name);
        debug("wtf="+i);
        return i;
    }

}
