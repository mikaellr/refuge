package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserAddServlet", urlPatterns = {"/gestion/user-add"})
public class UserAddServlet extends GestionServlet {

    // formulaire ajout
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/gestion/user-add.jsp").forward(request, response);
    }

    // ajout d'un user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        User user = getGestionService().addUser(firstName, lastName, email, phone, password, confirm);
        sendRedirect(response, "/gestion/users");
    }

}
