package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.Species;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/animals")
@Produces(MediaType.APPLICATION_JSON)
public class AnimalResource extends BaseResource {

    @GET
    @Path("/{id}")
    public Response one(@PathParam("id") Long id) {
        Animal item = getBeanService().getAnimal(id);
        return Response.status(200).entity(item).build();
    }

    /**
     * @return
     */
    @GET
    @Path("")
    public Response all() {
        List<Animal> items = getBeanService().getAnimals();
        GenericEntity<List<Animal>> animalsEntity = new GenericEntity<List<Animal>>(items) {};
        return Response.status(200).entity(animalsEntity).build();
    }

    @GET
    @Path("/species/{id}")
    public Response findBySpecies(@PathParam("id") Long id) {
        List<Animal> items = getBeanService().getAnimalsBySpecies(id);
        GenericEntity<List<Animal>> animalsEntity = new GenericEntity<List<Animal>>(items) {};
        return Response.status(200).entity(animalsEntity).build();
    }

    @GET
    @Path("/races/{id}")
    public Response findByRaces(@PathParam("id") Long id) {
        List<Animal> items = getBeanService().getAnimalsByRaces(id);
        GenericEntity<List<Animal>> animalsEntity = new GenericEntity<List<Animal>>(items) {};
        return Response.status(200).entity(animalsEntity).build();
    }

    @GET
    @Path("/?species={species-id}&race={race-id}&offset={offset}&limit={limit}&last={last}&adoptable={adoptable}&all={all}")
    public Response getAnimals(@PathParam("species-id") Long species_id, @PathParam("race-id") Long race_id,
                               @PathParam("offset") Long offset, @PathParam("limit") Long limit,
                               @PathParam("last") Boolean last, @PathParam("adoptable") Boolean adoptable,
                               @PathParam("all") Boolean all) {

        List<Animal> result = getBeanService().getAnimalsByParameters(species_id, race_id, adoptable,all, last,limit, offset);

        if (result.isEmpty()) {
            return Response.status(404).build();
        } else {
            return Response.status(200).entity(result).build();
        }
    }

}
