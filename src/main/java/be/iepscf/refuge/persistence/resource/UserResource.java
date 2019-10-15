package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends BaseResource {

    @GET
    @Path("/{param}")
    public Response one(@PathParam("param") Long id) {
        User user = getBeanService().getUser(id);
        return Response.status(200).entity(user).build();
    }

    @GET
    @Path("/")
    public Response all() {
        List<User> users = getBeanService().getUsers();
        GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
        return Response.status(200).entity(usersEntity).build();
    }

    @POST
    @Path("/")
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
        return Response.created(new URI("/rest/user/" + user.getId())).build();
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
            getBeanService().deleteUser(user);
            return Response.status(202).entity("Employee deleted successfully !!").build();
        }
        return Response.status(400).entity("User to delete not found").build();
    }

}
