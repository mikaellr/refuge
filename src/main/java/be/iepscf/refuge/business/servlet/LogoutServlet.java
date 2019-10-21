package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.servlet.util.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends PublicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * formulaire de logout
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (! hasUser(request)) {
			Logger.getLogger().debug("login GET : not logged");
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/logout.jsp").forward(request, response);
		}
	}

	/**
	 * logout
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		Logger.getLogger().debug("logging out : " + user);
		request.getSession().setAttribute("user", null);
		HttpSession session = request.getSession(false);
		if (session != null){
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
