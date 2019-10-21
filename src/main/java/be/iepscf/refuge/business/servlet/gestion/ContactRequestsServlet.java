package be.iepscf.refuge.business.servlet.gestion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import be.iepscf.refuge.business.businessbean.ContactRequest;

@WebServlet(name = "ContactRequestsServlet", urlPatterns = {"/gestion/contact-requests"})
//@WebServlet("/gestion/contact-requests")
public class ContactRequestsServlet extends GestionServlet {


    /**
     * liste des CRs, liens vers fiches
     *
     * idéalement, séparer les traitées des non traitées
     */
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

}
