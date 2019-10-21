package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AnimalListServlet", urlPatterns = {"/gestion/animals"})
public class AnimalListServlet extends GestionServlet {

    /**
     * liste des animaux :
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Animal> animals = getGestionService().getAnimals();
        request.setAttribute("animals", animals);
        request.getRequestDispatcher("/WEB-INF/jsp/gestion/animals.jsp").forward(request, response);
    }

}
