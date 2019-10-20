package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.businessbean.ContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static be.iepscf.refuge.business.service.ServiceFactory.getGestionService;


/*
contact-request?id=<animal-id>
be.iepscf.refuge.business.servlet.ContactRequestServlet
    doGet       formulaire de la demande de contact
                jsp : /contact-request.jsp
    doPost      enregistrement de la demande de contact
                (?) retour à (fiche animal | accueil) ou message de remerciement
                (?) jsp : /contact-request-confirmation.jsp (si option affichage message de remerciement)
 */
@WebServlet("/contact-request")
public class ContactRequestServlet extends PublicServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = getParameter(request,"id");
        ContactRequest contactRequest = getGestionService().getContactRequests(id);

        if (contactRequest == null) {
            send404(request, response, String.format("Contact requestnumber # not found", id));
            return;
        }
        request.setAttribute("item", contactRequest);
        request.getRequestDispatcher("WEB-INF/jsp/gestion/contact-request.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = getParameter(request,"id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");
        boolean treated = Boolean.parseBoolean(request.getParameter("treated"));
        ContactRequest contactRequest = getGestionService().updateContactRequest(id, firstName, lastName, email, phone, message, treated);
        sendRedirect(response, "/gestion/contact-request");
    }
}
