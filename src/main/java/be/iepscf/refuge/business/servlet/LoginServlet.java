package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.servlet.util.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends PublicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * formulaire de login
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (hasUser(request)) {
			Logger.getLogger().debug("login GET : already logged");
			response.sendRedirect(request.getContextPath() + "/logout");
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

	/**
	 * login
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (hasUser(request)) {
			Logger.getLogger().debug("login POST : already logged");
			response.sendRedirect(request.getContextPath() + "/logout");
			return;
		}
		Logger.getLogger().debug(String.format("encoding=%s, contentType=%s", request.getCharacterEncoding(), request.getContentType()));
		request.setCharacterEncoding("UTF-8");
		Logger.getLogger().debug(String.format("encoding=%s, contentType=%s", request.getCharacterEncoding(), request.getContentType()));
		String pEmail = request.getParameter("email");
		String pPassword = request.getParameter("password");
		if (pEmail != null && pPassword != null) {
			Logger.getLogger().debug(String.format("email=%s, password=%s", pEmail, pPassword));
			User user = getPublicService().login(pEmail, pPassword);
			if (user != null) {
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) {
					oldSession.invalidate();
				}
				HttpSession newSession = request.getSession(true);
				newSession.setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/logout");
				return;
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

}
