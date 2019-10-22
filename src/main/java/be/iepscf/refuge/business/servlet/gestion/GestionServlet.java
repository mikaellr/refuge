package be.iepscf.refuge.business.servlet.gestion;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.service.GestionService;
import be.iepscf.refuge.business.service.ServiceFactory;
import be.iepscf.refuge.business.servlet.PublicServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet abstraite de base pour toutes les servlets servant de contrôlleur à un action "gestion" (utilisateur connecté)
 *
 * Fournit la méthode getGestionService() pour accéder au service GestionService proposant les accès au modéle et la logique métier associée
 */
@WebServlet(name = "GestionServlet")
public abstract class GestionServlet extends PublicServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public GestionService getGestionService() {
        return ServiceFactory.getGestionService();
    }

    protected User getUserParameter(HttpServletRequest request, String name) {
        Long id = getLongParameter(request, name);
        if (id != null && id > 0) {
            User item = getGestionService().getUser(id);
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    protected ContactRequest getContactRequestParameter(HttpServletRequest request, String name) {
        Long id = getLongParameter(request, name);
        if (id != null && id > 0) {
            ContactRequest item = getGestionService().getContactRequest(id);
            if (item != null) {
                return item;
            }
        }
        return null;
    }

}
