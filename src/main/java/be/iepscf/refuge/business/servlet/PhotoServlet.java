package be.iepscf.refuge.business.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.service.PublicService;

/**
 * Servlet implementation class PhotoServlet
 */
@WebServlet("/photo")
public class PhotoServlet extends PublicServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		if (idString != null) {
			Long id = Long.parseLong(idString);
			Animal animal = getPublicService().getAnimal(id);
			if (animal != null) {
				byte[] content = animal.getPhoto();
				String contentType = animal.getPhotoContentType();
				if (content != null && content.length > 0 && contentType != null && contentType.length() > 0) {
					response.setContentType(contentType);
					response.getOutputStream().write(content);
					return;
				}
			}
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		response.getWriter().println("Not Found");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
