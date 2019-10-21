package be.iepscf.refuge.business.servlet.gestion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import be.iepscf.refuge.business.businessbean.ContactRequest;

@WebServlet(name = "ContactRequestServlet", urlPatterns = {"/gestion/contact-request"})
//@WebServlet("/gestion/contact-requests")
public class ContactRequestsServlet extends GestionServlet {
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
