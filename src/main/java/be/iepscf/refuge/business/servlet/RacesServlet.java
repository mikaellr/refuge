package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class PhotoServlet
 */
@WebServlet("/races")
public class RacesServlet extends PublicServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RacesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		if (idString != null) {
			Long id = Long.parseLong(idString);
			Species species = getPublicService().getSpecies(id);
			List<Race> races = getPublicService().getRacesBySpecies(species);
			Gson gson = new Gson();
			String json = gson.toJson(races);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(200);
			response.getWriter().write(json);
			return;
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
