package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.service.GestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AnimalServlet
 */
@WebServlet(value = "/contact")
public class ContactServlet extends PublicServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp").forward(request, response);
	}

}
