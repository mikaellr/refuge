package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.Race;
import be.iepscf.refuge.persistence.entitybean.Species;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/species")
@Produces(MediaType.APPLICATION_JSON)
public class SpeciesResource extends BaseResource {

    @GET
    public Response all() {
        List<Species> items = getBeanService().getSpecies();
        GenericEntity<List<Species>> entities = new GenericEntity<List<Species>>(items) {};
        return Response.status(200).entity(entities).build();
    }

    @GET
    @Path("/{id}")
    public Response one(@PathParam("id") Long id) {
        Species item = getBeanService().getSpecies(id);
        if (item == null) {
            notFound();
        }
        return Response.status(200).entity(item).build();
    }

    @GET
    @Path("/{id}/races")
    public Response racesBySpecies(@PathParam("id") Long id) {
        List<Race> result = getBeanService().getRacesBySpecies(id);
        return Response.status(200).entity(result).build();
    }

}
