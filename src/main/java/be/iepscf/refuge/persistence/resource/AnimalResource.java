package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.Animal;
import be.iepscf.refuge.persistence.entitybean.ContactRequest;
import be.iepscf.refuge.persistence.entitybean.Species;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@Path("/animals")
@Produces(MediaType.APPLICATION_JSON)
public class AnimalResource extends BaseResource {

    @GET
    @Path("/{id}")
    public Response one(@PathParam("id") Long id) {
        Animal item = getBeanService().getAnimal(id);
        if (item == null) {
            return notFound();
        }
        return Response.status(200).entity(item).build();
    }

    /**
     * @return
     */
    @GET
    public Response many(@QueryParam("species") Long species_id,
                         @QueryParam("race") Long race_id,
                         @QueryParam("offset") Long offset,
                         @QueryParam("limit") Long limit,
                         @QueryParam("last") Boolean last,
                         @QueryParam("adoptable") Boolean adoptable,
                         @QueryParam("all") Boolean all) {
        debug("wss animals many");
        debug("wss animals many : species_id = " + species_id);
        debug("wss animals many : race_id = " + race_id);
        debug("wss animals many : all = " + all);
        debug("wss animals many : adoptable = " + adoptable);
        debug("wss animals many : offset = " + offset);
        debug("wss animals many : limit = " + limit);

        List<Animal> items;
        if (species_id == null && race_id == null && offset == null && limit ==null && last == null &&adoptable == null && all == null) {
            items = getBeanService().getAnimals();
        } else {
            items = getBeanService().getAnimalsByParameters(species_id, race_id, adoptable,all, last,limit, offset);
        }
        GenericEntity<List<Animal>> animalsEntity = new GenericEntity<List<Animal>>(items) {};
        if (items== null) {
            return Response.status(500).build();
        }
        if (items.isEmpty()) {
            // le résultat PEUT être vide, pas d'erreur 404...
        }
        return Response.status(200).entity(items).build();
    }


    /**
     * ne fonctionne pas car tous les paramètres sont requis
     *
     * (exemple : /rest/animals/?species=1&race=24 est renvoyé vers méthode all()...)
     */
    /*@GET
    @Path("/?species={species-id}&race={race-id}&offset={offset}&limit={limit}&last={last}&adoptable={adoptable}&all={all}")
    public Response getAnimalsDeprecated(@PathParam("species-id") Long species_id,
                                         @PathParam("race-id") Long race_id,
                                         @PathParam("offset") Long offset,
                                         @PathParam("limit") Long limit,
                                         @PathParam("last") Boolean last,
                                         @PathParam("adoptable") Boolean adoptable,
                                         @PathParam("all") Boolean all) {
        debug("wss animals getAnimals");
        return many(species_id, race_id, adoptable, all, last, limit, offset);
    }*/



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Animal item) throws URISyntaxException
    {
        if (item == null) {
            return Response.status(400).entity("bad request, empty").build();
        }
        debug("wss animals save : item = " + item);
        long id = getBeanService().saveAnimal(item);
        debug("wss animals save : id = " + id);
        if (id > 0) {

            return Response.created(new URI("/rest/animals/" + item.getId())).entity(item).build();
        } else {
            return Response.status(500).entity("failed").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Animal item) throws URISyntaxException
    {
        debug("wss animals update");
        if (item == null) {
            return Response.status(400).entity("bad request, empty").build();
        }
        debug("wss animals update : item = " + item);
        long aff = getBeanService().updateAnimal(item);
        debug("wss animals update : aff = " + aff);
        if (aff > 0) {
            return Response.status(200).entity(item).build();
        } else {
            return Response.status(500).entity("failed").build();
        }
    }







    /**
     * should be at /rest/species/{id}/animals
     * (no usage, ok)
     */
    @GET
    @Path("/species/{id}")
    public Response findBySpecies(@PathParam("id") Long id) {
        List<Animal> items = getBeanService().getAnimalsBySpecies(id);
        GenericEntity<List<Animal>> animalsEntity = new GenericEntity<List<Animal>>(items) {};
        return Response.status(200).entity(animalsEntity).build();
    }

    /**
     * should be at /rest/races/{id}/animals
     * (no usage, ok)
     */
    @GET
    @Path("/races/{id}")
    public Response findByRaces(@PathParam("id") Long id) {
        List<Animal> items = getBeanService().getAnimalsByRaces(id);
        GenericEntity<List<Animal>> animalsEntity = new GenericEntity<List<Animal>>(items) {};
        return Response.status(200).entity(animalsEntity).build();
    }



    @GET
    @Path("/{id}/contact-requests")
    public Response getAnimalContactRequests(@PathParam("id") Long id) {
        Animal animal = getBeanService().getAnimal(id);
        if (animal != null) {
            List<ContactRequest> items = getBeanService().getContactRequestsByAnimal(animal);
            return Response.status(200).entity(items).build();
        }
        return notFound();
    }

}
