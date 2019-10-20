package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.businessbean.Animal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class AnimalServlet
 */
@WebServlet("/animals")
public class AnimalsServlet extends PublicServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ceci est temporaire :
		List<Animal> animals = getPublicService().getAnimals();
		// ceci est à implémenter :
		//List<Animal> animals = getPublicService().getAnimalsQuery();
		request.setAttribute("animals", animals);
		request.getRequestDispatcher("/WEB-INF/jsp/animals.jsp").forward(request, response);
	}

}
