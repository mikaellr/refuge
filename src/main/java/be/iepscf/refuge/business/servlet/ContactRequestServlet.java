package be.iepscf.refuge.business.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
