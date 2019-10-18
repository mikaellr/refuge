package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.service.PublicService;
import be.iepscf.refuge.business.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * servlet abstraite de base pour toutes les servlets servant de contrôlleur à une action visiteur (utilisateur non connecté)
 *
 * Fournit la méthode getPublicService() pour accéder au service PublicService proposant les accès au modéle et la logique métier
 * nécessaire au fonctionnement de la partie publique du site
 */
@WebServlet(name = "PublicServlet")
public abstract class PublicServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



    public PublicService getPublicService() {
        return ServiceFactory.getPublicService();
    }


    protected void sendRedirect(HttpServletResponse response, String url) throws IOException {
        if (url.startsWith("/")) {
            url = "/refuge" + url;
        }
        response.sendRedirect(url);
    }

    protected void send404(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException, ServletException {
        //response.sendError(HttpServletResponse.SC_NOT_FOUND);
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().write(msg);
        //request.getRequestDispatcher("/notfound.jpg").forward(request, response);
    }

    protected Long getParameter(HttpServletRequest request, String name) {
        return Long.parseLong(request.getParameter("id"));
    }

    /* récupère contenu textuel d'une URL, si besoin pour requêtes artisanales sans framework */
    public String retrieve(String url) throws IOException {
        StringBuffer textResponse = new StringBuffer(100);
        URL realUrl = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(realUrl.openStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            textResponse.append(line);
        }
        reader.close();
        return textResponse.toString();
    }

}
