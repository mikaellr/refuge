package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.Color;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/colors")
@Produces(MediaType.APPLICATION_JSON)
public class ColorsResource extends BaseResource {

    @GET
    public Response all() {
        List<Color> items = getBeanService().getColors();
        GenericEntity<List<Color>> entities = new GenericEntity<List<Color>>(items) {};
        return Response.status(200).entity(entities).build();
    }

    @GET
    @Path("/name/{name}")
    public Response byName(@PathParam("name") String name) {
        Color item = getBeanService().getColorByName(name);
        if (item == null) {
            return notFound();
        }
        return Response.status(200).entity(item).build();
    }

    @GET
    @Path("/{id}")
    public Response one(@PathParam("id") Long id) {
        Color item = getBeanService().getColor(id);
        if (item == null) {
            return notFound();
        }
        return Response.status(200).entity(item).build();
    }

}
