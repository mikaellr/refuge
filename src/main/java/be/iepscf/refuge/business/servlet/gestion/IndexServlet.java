package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Color;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.servlet.PublicServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns = {"/gestion/"})
public class IndexServlet extends GestionServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Animal> animals = getPublicService().getAnimals();
		List<User> users = getGestionService().getUsers();
		List<Species> species = getPublicService().getSpecies();
		List<Color> colors = getPublicService().getColors();
		request.setAttribute("animals", animals);
		request.setAttribute("users", users);
		request.setAttribute("species", species);
		request.setAttribute("colors", colors);
		request.getRequestDispatcher("/WEB-INF/jsp/gestion/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
