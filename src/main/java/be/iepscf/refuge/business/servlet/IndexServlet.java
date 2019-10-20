package be.iepscf.refuge.business.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.iepscf.refuge.business.businessbean.Species;

@WebServlet(urlPatterns = {""})
public class IndexServlet extends PublicServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * appelle la page d'accueil en lui envoyant les Species pour remplir le formulaire de recherche
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Species> species = getPublicService().getSpecies();
		request.setAttribute("species", species);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

}
