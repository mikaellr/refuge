package be.iepscf.refuge.business.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.business.service.GestionService;



@WebServlet("/Contact-Request")
public class ContactRequestServlet extends PublicServlet {
    private static final long serialVersionUID = 1L;

    public ContactRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String pId = request.getParameter("id");
        if (pId != null) {
            try {
                Long id = Long.parseLong(pId);
                GestionService es = new GestionService();
                ContactRequest contactRequest = es.getContactRequest(id);
                if (contactRequest != null) {
                    request.setAttribute("contactRequest", contactRequest);
                    request.getRequestDispatcher("/WEB-INF/jsp/Contact-Request.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
            }
        }
        System.err.println("sending 404 for Contact-Request (pId=" + pId);
        send404(request, response, "Contact-Request not found");
        //request.getRequestDispatcher("/WEB-INF/jsp/contact-Request.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
