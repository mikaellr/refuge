package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.util.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/animals")
public class AnimalsServlet extends PublicServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * page de résultats de recherche d'animaux
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Species species = getSpeciesParameter(request);
		Race race = getRaceParameter(request);
		Long offset = getLongParameter(request, "offset");
		Long limit = getLongParameter(request, "limit");
		Boolean last = getBooleanParameter(request, "last");
		Boolean adoptable = getBooleanParameter(request, "adoptable");
		Boolean all = getBooleanParameter(request, "all");
        Logger.getLogger().debug("Paramètres AnimalsServlet :");
		Logger.getLogger().debug("species=" + species);
		Logger.getLogger().debug("race=" + race);
        Logger.getLogger().debug("limit=" + limit);
        Logger.getLogger().debug("last=" + last);
        Logger.getLogger().debug("adoptable=" + adoptable);
		Logger.getLogger().debug("all=" + all);
		List<Animal> animals = getPublicService().getAnimalsQuery(species, race, offset, limit, last, adoptable, all);
		request.setAttribute("animals", animals);


		List<Species> allSpecies = getPublicService().getSpecies();
		List<Race> allRaces = species != null ? getPublicService().getRacesBySpecies(species) : new ArrayList<Race>();
		request.setAttribute("species", allSpecies);
		request.setAttribute("races", allRaces);
		request.setAttribute("selectedSpeciesId", species != null ? species.getId() : null);
		request.setAttribute("selectedRaceId", race != null ? race.getId() : null);
		request.getRequestDispatcher("/WEB-INF/jsp/animals.jsp").forward(request, response);
	}

}
