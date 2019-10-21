package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Role;
import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.mao.RoleMAO;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class WebServiceRoleMAO extends WebServiceGenericMAO<Role, Long> implements RoleMAO {

    public WebServiceRoleMAO() {
        entityUrl = "roles";
    }

    @Override
    public List<Role> get() {
        Response response = getEntityWebTarget().request(MediaType.APPLICATION_JSON).get();
        List<Role> items = response.readEntity(new GenericType<List<Role>>() {});
        return items;
    }

}
