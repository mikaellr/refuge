package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.entitybean.Animal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
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
        List<Animal> transition = null;

        //Structures conditionnelles, "affinant" la liste :

        //Sélection de l'espèce
        for ( Animal animal:result) {
            if(animal.getSpecies().getId() == species_id){
                transition.add(animal);
            }
        }
        result.clear();
        //Sélection de la race
        for ( Animal animal:transition) {
            if(animal.getRace().getId() == race_id){
                result.add(animal);
            }
        }
        transition.clear();
        //Adoptable
        for ( Animal animal:result) {
            if(animal.isAdoptable()){
                transition.add(animal);
            }
        }
        // Première partie bouclée :

        result = transition;
        transition = null;

        //Nombres de valeurs "affichées"
        if(all){
            //On garde tout
        }
        else{
            if(offset != null && limit != null){
                //Prennent tous les deux la valeur indiquée
                int i=limit.intValue();
                transition = result.subList(0,i);
                i = offset.intValue();
                Collections.rotate(transition,i);
            }
            else if(offset == null && limit != null){
                //offset à 0, limite reste prend la valeur indiquée
                int i=limit.intValue();
                transition = result.subList(0,i);
            }
            else if(offset != null && limit == null){
                //offset prend la valeur indiquée, limit reste à 20
                int i = offset.intValue();
                Collections.rotate(transition,i);
                transition = result.subList(0,20);
            }

            else {
                //offset à 0, limit à 20
                transition = result.subList(0,20);
            }
        }
        result = transition;
        //Affichage ASC ou DESC
        if(last){
            //Liste reste en place
        }
        else{
            Collections.reverse(result);
        }

        if (result.isEmpty()) {
            return Response.status(404).build();
        } else {
            return Response.status(200).entity(result).build();
        }
    }
}
