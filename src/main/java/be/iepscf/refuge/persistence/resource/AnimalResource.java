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
     * @todo redondance cyclique species > races > species > ...
     * @return
     */
    @GET
    @Path("/")
    public Response all() {
        List<Animal> items = getBeanService().getAnimals();
        GenericEntity<List<Animal>> usersEntity = new GenericEntity<List<Animal>>(items) {};
        return Response.status(200).entity(items).build();
    }


}
