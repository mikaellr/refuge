package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.Role;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource extends BaseResource {

    @GET
    public Response all() {
        List<Role> items = getBeanService().getRoles();
        GenericEntity<List<Role>> entities = new GenericEntity<List<Role>>(items) {};
        return Response.status(200).entity(entities).build();
    }

    @GET
    @Path("/{id}")
    public Response one(@PathParam("id") Long id) {
        Role item = getBeanService().getRole(id);
        if (item == null) {
            notFound();
        }
        return Response.status(200).entity(item).build();
    }

}
