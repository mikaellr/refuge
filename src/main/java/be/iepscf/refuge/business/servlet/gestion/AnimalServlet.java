package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AnimalServlet", urlPatterns = {"/gestion/animal"})
public class AnimalServlet extends GestionServlet {

    /**
     * afficher formulaire édition/update animal
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Animal animal = getAnimalParameter(request, "id");

        Species selectedSpecies = animal.getSpecies();
        Race selectedRace = animal.getRace();
        Long selectedSpeciesId = selectedSpecies.getId();
        Long selectedRaceId = selectedRace != null ? selectedRace.getId() : null;

        request.setAttribute("item", animal);
        request.setAttribute("speciesAll", getPublicService().getSpecies());
        request.setAttribute("racesAll", getPublicService().getRacesBySpecies(animal.getSpecies()));
        request.setAttribute("colorsAll", getPublicService().getColors());
        request.setAttribute("selectedSpeciesId", selectedSpeciesId);
        request.setAttribute("selectedRaceId", selectedRaceId);
        request.getRequestDispatcher("/WEB-INF/jsp/gestion/animal.jsp").forward(request, response);
    }

    /**
     * update animal
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = getLongParameter(request, "id");
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        Boolean sterilized = getBooleanParameter(request,"sterilized");
        Boolean adoptable = getBooleanParameter(request,"adoptable");
        Animal animal = getGestionService().getAnimal(id);
        if (animal == null) {
            send404(request, response,"animal not found");
            return;
        }
        debug("steri" + sterilized);
        debug("adoptable" +adoptable);
        debug("AnimalServlet post animal =" + animal);
    }

}
