package be.iepscf.refuge.business.service;

import be.iepscf.refuge.business.businessbean.User;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class WebServiceClientService {

    protected String targetUrl = "https://refuge.iepscf.be/rest";


    public WebServiceClientService() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("business/business.properties"));
            targetUrl = properties.getProperty("rest.url", targetUrl);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public List<User> getUsers()  {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response resp = invocationBuilder.get();
        List<User> users = resp.readEntity(new GenericType<List<User>>() {});
        return users;
    }

    public User getUser(Long id)  {
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

    public long saveUser(User user) {

        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

        System.out.println("saveUser status : " + response.getStatus());
        System.out.println("saveUser response : " + response.readEntity(String.class));

        return -1;
    }

    public long updateUser(User user) {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users").path(user.getId().toString());

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));

        System.out.println("updateUser status : " + response.getStatus());
        System.out.println("updateUser response : " + response.readEntity(User.class));
        return -1;
    }

    public long deleteUser(User user) {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl).path("users").path(user.getId().toString());

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete();

        System.out.println("deleteUser status : " + response.getStatus());
        System.out.println("deleteUser response : " + response.readEntity(String.class));
        return -1;
    }

}
