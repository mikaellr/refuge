package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.service.ServiceFactory;
import be.iepscf.refuge.business.servlet.PublicServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDeleteServlet", urlPatterns = {"/gestion/user-delete"})
public class UserDeleteServlet extends GestionServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = getParameter(request, "id");
        ServiceFactory.getGestionService().deleteUser(id);
        sendRedirect(response, "/gestion/users");
    }

}
