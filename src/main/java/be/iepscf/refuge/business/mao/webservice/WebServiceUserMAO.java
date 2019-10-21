package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.mao.UserMAO;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class WebServiceUserMAO extends WebServiceGenericMAO<User, Long> implements UserMAO {

    @Override
    public List<User> get()  {
        debug("retrieving list of user by ws");
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response resp = invocationBuilder.get();
        List<User> users = resp.readEntity(new GenericType<List<User>>() {});
        return users;
    }

    @Override
    public User get(Long id)  {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users").path(id.toString());
        //WebTarget helloworldWebTargetWithQueryParam =                webTarget.queryParam("greeting", "Hi World!");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response resp = invocationBuilder.get();
        debug("wsc user get : status = " + resp.getStatus());
        if (resp.getStatus() == 200) {
           User user = resp.readEntity(User.class);
           if (user != null) {
               debug("wsc user get : user = " + user);
               return user;
           }
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users").path("email").path(email);
        //WebTarget helloworldWebTargetWithQueryParam =                webTarget.queryParam("greeting", "Hi World!");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response resp = invocationBuilder.get();
        debug("wsc user getByEmail : status = " + resp.getStatus());
        if (resp.getStatus() == 200) {
            User user = resp.readEntity(User.class);
            if (user != null) {
                debug("wsc user getByEmail : user = " + user);
                return user;
            }
        }
        return null;
    }

    @Override
    public long save(User user) {

        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

        debug("wsc user mao save : status = " + response.getStatus());
        //debug("saveUser response : " + response.readEntity(String.class));
        if (response.getStatus() == 200) {
            long id = response.readEntity(Long.class);
            if (id > 0) {
                debug("wsc user mao save id: id = " + id);
                user.setId(id);
                return id;
            }
        }
        return -1;
    }

    @Override
    public long update(User user) {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users").path(user.getId().toString());

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));

        debug("updateUser status : " + response.getStatus());
        debug("updateUser response : " + response.readEntity(User.class));
        return -1;
    }

    @Override
    public long delete(User user) {
        Client client = ClientBuilder.newClient( new ClientConfig());
        debug("wsc user deleting : " + user);

        WebTarget webTarget = client.target(targetUrl).path("users").path(user.getId().toString());

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();

        debug("wsc user delete : status = " + response.getStatus());
        debug("wsc user delete : resp = " + response.readEntity(String.class));
        return -1;
    }

}
