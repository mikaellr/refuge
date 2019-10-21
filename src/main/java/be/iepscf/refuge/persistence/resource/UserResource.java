package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.transform.sax.SAXSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends BaseResource {

    @GET
    public Response getAll() {
        List<User> users = getBeanService().getUsers();
        GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
        return Response.status(200).entity(usersEntity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(User user) throws URISyntaxException
    {
        if (user == null) {
            return Response.status(400).entity("Please add employee details !!").build();
        }
        if(user.getEmail() == null) {
            return Response.status(400).entity("Please provide the email !!").build();
        }
        System.out.println("user received by api for saving:" + user);
        getBeanService().saveUser(user);
        System.out.println("user saved" + user);

        return Response.status(200).entity(Long.valueOf(user.getId())).build();
        //return Response.created(new URI("/rest/user/" + user.getId())).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        User user = getBeanService().getUser(id);
        if (user != null) {
            return Response.status(200).entity(user).build();
        }
        return Response.status(404).entity("resource not found").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, User user)
    {
        if (user == null) {
            return Response.status(400).entity("Please add employee details !!").build();
        }
        if (user.getEmail() == null) {
            return Response.status(400).entity("Please provide the email !!").build();
        }
        System.out.println("user received by api for updating:" + user);
        getBeanService().updateUser(user);
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id)
    {
        User user = getBeanService().getUser(id);
        if (user != null) {
            Long aff = getBeanService().deleteUser(user);
            return Response.status(202).entity("user delete affected rows : " + aff).build();
        }
        return Response.status(400).entity("User to delete not found").build();
    }

    @GET
    @Path("/email/{email}")
    public Response get(@PathParam("email") String email) {
        System.out.println("query by email:"+email);
        User user = getBeanService().getUserByEmail(email);
        if (user != null) {
            return Response.status(200).entity(user).build();
        }
        return Response.status(404).entity("resource not found").build();
    }

}
