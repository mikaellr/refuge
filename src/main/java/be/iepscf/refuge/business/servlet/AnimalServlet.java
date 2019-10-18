package be.iepscf.refuge.business.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.service.GestionService;

/**
 * Servlet implementation class AnimalServlet
 */
@WebServlet("/animal")
public class AnimalServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String pId = request.getParameter("id");
		if (pId != null) {
			try {
				Long id = Long.parseLong(pId);
				GestionService es = new GestionService();
				Animal animal = es.getAnimal(id);
				if (animal != null) {
					request.setAttribute("animal", animal);
					request.getRequestDispatcher("/WEB-INF/jsp/animal.jsp").forward(request, response);
					return;
				}
			} catch (NumberFormatException e) {
			}
		}
		System.err.println("sending 404 for animal (pId=" + pId);
		send404(request, response, "animal not found");
		//request.getRequestDispatcher("/WEB-INF/jsp/animal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
