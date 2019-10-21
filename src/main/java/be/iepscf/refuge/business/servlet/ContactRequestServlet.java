package be.iepscf.refuge.business.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.business.service.GestionService;
import be.iepscf.refuge.business.servlet.util.Logger;


@WebServlet("/contact-request")
public class ContactRequestServlet extends PublicServlet {
    private static final long serialVersionUID = 1L;

    /**
     * formulaire demande de contact pour un animal
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Animal animal = getAnimalParameter(request, "id");
        if (animal == null) {
            send404(request, response, "animal not found");
        }
        request.setAttribute("animal", animal);
        request.getRequestDispatcher("/WEB-INF/jsp/contact-request.jsp").forward(request, response);
    }

    /**
     * ajout demande de contact pour l'animal
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Animal animal = getAnimalParameter(request, "id");
        if (animal == null) {
            send404(request, response, "animal not found");
            return;
        }
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");
        ContactRequest cr = getPublicService().addContactRequest(firstName, lastName, email, phone, message, animal.getId());
        if (cr == null) {
            Logger.getLogger().debug("cr not created");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/contact-request-confirmation.jsp").forward(request, response);
    }

}
