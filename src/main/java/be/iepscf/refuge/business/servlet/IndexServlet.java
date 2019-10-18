package be.iepscf.refuge.business.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Color;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.service.PublicService;
import be.iepscf.refuge.business.service.ServiceFactory;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns = {""})
public class IndexServlet extends HttpServlet {
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
		PublicService publicService = ServiceFactory.getPublicService();;
		List<Animal> animals = publicService.getAnimals();
		List<User> users = ServiceFactory.getGestionService().getUsers();
		List<Species> species = publicService.getSpecies();
		List<Color> colors = publicService.getColors();
		request.setAttribute("animals", animals);
		request.setAttribute("users", users);
		request.setAttribute("species", species);
		request.setAttribute("colors", colors);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
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
