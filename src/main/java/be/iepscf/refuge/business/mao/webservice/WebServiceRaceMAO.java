package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Role;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.RaceMAO;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class WebServiceRaceMAO extends WebServiceGenericMAO<Race, Long> implements RaceMAO {

    public WebServiceRaceMAO() {
        entityUrl = "races";
    }

    @Override
    public List<Race> get() {
        Response response = getEntityWebTarget().request(MediaType.APPLICATION_JSON).get();
        List<Race> items = response.readEntity(new GenericType<List<Race>>() {});
        return items;
    }

    @Override
    public List<Race> getBySpecies(Species species) {
        return getBySpecies(species.getId());
    }

    @Override
    public List<Race> getBySpecies(Long id) {
        Response response = getWebTarget().path("species").path(id.toString()).path("races").request(MediaType.APPLICATION_JSON).get();
        List<Race> items = response.readEntity(new GenericType<List<Race>>() {});
        return items;
    }

}
