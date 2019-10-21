package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Role;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.mao.SpeciesMAO;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
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

        //return retrieve(name)
    /*    String resource_base = "species";
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users").path(id.toString());
        //WebTarget helloworldWebTargetWithQueryParam =                webTarget.queryParam("greeting", "Hi World!");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response resp = invocationBuilder.get();

        User user = resp.readEntity(User.class);
        System.out.println("user status:" + resp.getStatus());
        System.out.println("user:" + user);
        return user;
*/
        return null;
    }

}
