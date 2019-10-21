package be.iepscf.refuge.business.servlet.gestion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AnimalServlet", urlPatterns = {"/gestion/animal"})
public class AnimalServlet extends GestionServlet {

    /**
     * afficher formulaire édition/update animal
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * update animal
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
