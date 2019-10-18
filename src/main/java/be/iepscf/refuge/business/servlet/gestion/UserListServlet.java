package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.service.ServiceFactory;
import be.iepscf.refuge.business.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserListServlet", urlPatterns = {"/gestion/users"})
public class UserListServlet extends BaseServlet {

    // liste des users
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = ServiceFactory.getGestionService().getUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/jsp/gestion/users.jsp").forward(request, response);
    }

    // ajout d'un user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        User user = ServiceFactory.getGestionService().addUser(firstName, lastName, email, phone, password, confirm);
        sendRedirect(response, "/gestion/users");
    }

}
