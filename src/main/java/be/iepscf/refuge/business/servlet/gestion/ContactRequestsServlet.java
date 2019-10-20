package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.persistence.entitybean.ContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
/gestion/contact-requests
        be.iepscf.refuge.business.servlet.gestion.ContactRequestsServlet
        doGet       liste des demandes de contact
        - lien pour chaque demande vers /gestion/contact-request?id=<contactrequest-id>
        - faire en sorte qu'on voit si plusieurs demandes pour un animal
        jsp : /gestion/contact-requests.jsp

 */
@WebServlet("/gestion/contact-requests")
public class ContactRequestsServlet extends GestionServlet {

    List<ContactRequest> contactRequestList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/gestion/contact-requests.jsp").forward(request, response);
    }
}
