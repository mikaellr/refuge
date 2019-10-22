package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.Race;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/races")
@Produces(MediaType.APPLICATION_JSON)
public class RacesResource extends BaseResource {

    @GET
    public Response all() {
        List<Race> items = getBeanService().getRaces();
        return Response.status(200).entity(items).build();
    }

    @GET
    @Path("/{id}")
    public Response one(@PathParam("id") Long id) {
        Race item = getBeanService().getRace(id);
        return Response.status(200).entity(item).build();
    }

    @GET
    @Path("/for_species/{id}")
    public Response bySpecies(@PathParam("id") Long id) {
        List<Race> items = getBeanService().getRacesBySpecies(id);
        return Response.status(200).entity(items).build();
    }

}
