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
        System.out.println("retrieving list of user by ws");
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

        User user = resp.readEntity(User.class);
        System.out.println("user status:" + resp.getStatus());
        System.out.println("user:" + user);
        return user;
    }

    @Override
    public long save(User user) {

        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

        System.out.println("saveUser status : " + response.getStatus());
        //System.out.println("saveUser response : " + response.readEntity(String.class));
        Long id = response.readEntity(Long.class);

        return id;
    }

    @Override
    public long update(User user) {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users").path(user.getId().toString());

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));

        System.out.println("updateUser status : " + response.getStatus());
        System.out.println("updateUser response : " + response.readEntity(User.class));
        return -1;
    }

    @Override
    public long delete(User user) {
        Client client = ClientBuilder.newClient( new ClientConfig());
        System.out.println("deleteUser user : " + user);

        WebTarget webTarget = client.target(targetUrl).path("users").path(user.getId().toString());

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();

        System.out.println("deleteUser status : " + response.getStatus());
        System.out.println("deleteUser response : " + response.readEntity(String.class));
        return -1;
    }

    @Override
    public User getByEmail(String email) {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users").path("email").path(email);
        //WebTarget helloworldWebTargetWithQueryParam =                webTarget.queryParam("greeting", "Hi World!");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response resp = invocationBuilder.get();
        User user = resp.readEntity(User.class);
        System.out.println("user status:" + resp.getStatus());
        System.out.println("user:" + user);
        return user;
    }

}
