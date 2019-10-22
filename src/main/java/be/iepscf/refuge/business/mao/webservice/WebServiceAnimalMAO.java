package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.AnimalMAO;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class WebServiceAnimalMAO extends WebServiceGenericMAO<Animal, Long> implements AnimalMAO {

    public WebServiceAnimalMAO() {
        entityUrl = "animals";
    }

    @Override
    public List<Animal> get() {
        Response response = getEntityWebTarget().request(MediaType.APPLICATION_JSON).get();
        List<Animal> items = response.readEntity(new GenericType<List<Animal>>() {});
        return items;
    }

    @Override
    public List<Animal> getQuery(Species species, Race race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all) {
        Long idSpecies = species != null ? species.getId() : null;
        Long idRace= race != null ? race.getId() : null;
        return getQuery(idSpecies, idRace, offset, limit, last, adoptable, all);
    }

    @Override
    public List<Animal> getQuery(Long species, Long race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all) {
        WebTarget target = getEntityWebTarget()
                .queryParam("species", species)
                .queryParam("race", race);
        debug("wsc animals getQuery : query = " + target.getUri());
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        List<Animal> items = response.readEntity(new GenericType<List<Animal>>() {});
        return items;
    }

    @Override
    public long update(Animal animal) {
        debug("wsc animals update");
        return update(animal, animal.getId().toString());
    }

    @Override
    public long delete(Animal animal) {
        return delete(animal, animal.getId().toString());
    }

}
