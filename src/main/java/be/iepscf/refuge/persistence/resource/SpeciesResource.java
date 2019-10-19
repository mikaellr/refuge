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
    @Path("/")
    public Response all() {
        List<Species> items = getBeanService().getSpecies();
        GenericEntity<List<Species>> entities = new GenericEntity<List<Species>>(items) {};
        return Response.status(200).entity(entities).build();
    }

    @GET
    @Path("/{id}")
    public Response one(@PathParam("id") Long id) {
        Species item = getBeanService().getSpecies(id);
        return Response.status(200).entity(item).build();
    }

    @GET
    @Path("/{species}")
    public Response racesBySpecies(@PathParam("species") Species species) {
        List<Race> result = getBeanService().getRacesBySpecies(species);
        return Response.status(200).entity(result).build();
    }

}
