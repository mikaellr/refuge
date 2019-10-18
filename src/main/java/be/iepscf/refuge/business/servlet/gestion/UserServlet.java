package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.service.ServiceFactory;
import be.iepscf.refuge.business.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = {"/gestion/user"})
public class UserServlet extends
        BaseServlet {

    // affichage formulaire edit du user
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = getParameter(request, "id");
        User user = ServiceFactory.getGestionService().getUser(id);
        if (user == null) {
            send404(request, response, String.format("User# not found", id));
            return;
        }
        request.setAttribute("item", user);
        request.getRequestDispatcher("/WEB-INF/jsp/gestion/user.jsp").forward(request, response);
    }

    // update du user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Long id = getParameter(request, "id");
        User user = ServiceFactory.getGestionService().updateUser(id, firstName, lastName, email, phone);
        sendRedirect(response, "/gestion/users");
    }

}
