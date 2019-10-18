package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.service.ServiceFactory;
import be.iepscf.refuge.business.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserActivateServlet", urlPatterns = {"/gestion/user-activate"})
public class UserActivateServlet extends
        BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = getParameter(request, "id");
        ServiceFactory.getGestionService().activateUser(id);
        sendRedirect(response, "/gestion/users");
    }

}
