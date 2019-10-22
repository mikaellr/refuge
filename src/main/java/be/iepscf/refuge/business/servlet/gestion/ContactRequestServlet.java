package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.ContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContactRequestServlet", urlPatterns = {"/gestion/contact-request"})
public class ContactRequestServlet extends GestionServlet {

    /**
     * afficher la CR avec un bouton "marquer comme traitée"
     * + un buuton retour liste
     * + idéalement nombre de CR du même animal et lien vers elles
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ContactRequest item = getContactRequestParameter(request, "id");
        if (item == null) {
            send404(request,response, "Contact request number not found");
            return;
        }
        request.setAttribute("item", item);
        request.getRequestDispatcher("/WEB-INF/jsp/gestion/contact-request.jsp").forward(request, response);

    }

    /**
     * marquer la CR comme traitée
     * (la sélectionner, modifier unqiuement setTreated(true), et l'updater
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = getLongParameter(request, "id");
        Boolean treated = getBooleanParameter(request, "treated", true);
        if (id != null && id > 0 && treated != null) {
            ContactRequest cr = getGestionService().updateContactRequestTreated(id, treated);
        }
        sendRedirect(response, "/gestion/contact-requests");
    }

}
