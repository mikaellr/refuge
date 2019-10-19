package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.Animal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/animal")
@Produces(MediaType.APPLICATION_JSON)
public class AnimalResource extends BaseResource {

    @GET
    @Path("/{param}")
    public Response one(@PathParam("param") Long id) {
        Animal item = getBeanService().getAnimal(id);
        return Response.status(200).entity(item).build();
    }

    /**
     * @return
     */
    @GET
    @Path("/")
    public Response all() {
        List<Animal> items = getBeanService().getAnimals();
        GenericEntity<List<Animal>> usersEntity = new GenericEntity<List<Animal>>(items) {};
        return Response.status(200).entity(items).build();
    }


    @GET
    @Path("/?species={species-id}&race={race-id}&offset={offset}&limit={limit}&last={last}&adoptable={adoptable}&all={all}")
    public Response getAnimals(@PathParam("species-id") Long species_id, @PathParam("race-id") Long race_id,
                               @PathParam("offset") Long offset, @PathParam("limit") Long limit,
                               @PathParam("last") Boolean last, @PathParam("adoptable") Boolean adoptable,
                               @PathParam("all") Boolean all) {

        List<Animal> result = getBeanService().getAnimals();

        //Structures conditionnelles, "affinant" la liste :

        //Sélection de l'espèce
        //Sélection de la race
        //Adoptable
        //Last
        //Offset (null ou valeur indiquée)
        //Limit (null ou valeur indiquée)
        //All (dépend des deux précédents)

        if (result.isEmpty()) {
            return Response.status(404).build();
        } else {
            return Response.status(200).entity(result).build();
        }
    }
}
