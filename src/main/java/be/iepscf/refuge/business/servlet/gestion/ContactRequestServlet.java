package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.ContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContactRequestServlet", urlPatterns = {"/gestion/contact-request"})
//@WebServlet("/gestion/contact-requests")
public class ContactRequestServlet extends GestionServlet {

    /**
     * afficher la CR avec un bouton "marquer comme traitée"
     * + un buuton retour liste
     * + idéalement nombre de CR du même animal et lien vers elles
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id=getLongParameter(request,"id");
        ContactRequest contactRequest= getGestionService().getContactRequests(id);
        if (contactRequest==null){
            send404(request,response,String.format("Contact requestnumber # not found",id));
            return;
            }
        request.setAttribute("item",contactRequest);
        request.getRequestDispatcher("WEB-INF/jsp/gestion/contact-request.jsp").forward(request, response);

    }

    /**
     * marquer la CR comme traitée
     * (la sélectionner, modifier unqiuement setTreated(true), et l'updater
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = getLongParameter(request, "id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message= request.getParameter("message");
        boolean treated= Boolean.parseBoolean(request.getParameter("treated"));
        ContactRequest contactRequest = getGestionService().updateContactRequest(id, firstName, lastName, email, phone, message, treated);
        sendRedirect(response, "/gestion/contact-request");
    }

}
