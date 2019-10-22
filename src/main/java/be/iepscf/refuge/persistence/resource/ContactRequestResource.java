package be.iepscf.refuge.persistence.resource;


import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.ContactRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/contact-requests")
@Produces(MediaType.APPLICATION_JSON)
public class ContactRequestResource extends BaseResource {

    @GET
    public Response getAll() {
        List<ContactRequest> users = getBeanService().getContactRequests();
        GenericEntity<List<ContactRequest>> usersEntity = new GenericEntity<List<ContactRequest>>(users) {};
        return Response.status(200).entity(usersEntity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(ContactRequest item) throws URISyntaxException
    {
        if (item == null) {
            return Response.status(400).entity("Please add employee details !!").build();
        }
        if (item.getEmail() == null) {
            return Response.status(400).entity("Please provide the email !!").build();
        }
        getBeanService().saveContactRequest(item);
        return Response.created(new URI("/rest/user/" + item.getId())).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        ContactRequest user = getBeanService().getContactRequest(id);
        if (user == null) {
            return notFound();
        }
        return Response.status(200).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, ContactRequest user)
    {
        if (user == null) {
            return Response.status(400).entity("Please add employee details !!").build();
        }
        if (user.getEmail() == null) {
            return Response.status(400).entity("Please provide the email !!").build();
        }
        getBeanService().updateContactRequest(user);
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id)
    {
        ContactRequest user = getBeanService().getContactRequest(id);
        if (user == null) {
            return notFound();
        }
        getBeanService().deleteContactRequest(user);
        return Response.status(202).entity("Employee deleted successfully !!").build();
    }

}
