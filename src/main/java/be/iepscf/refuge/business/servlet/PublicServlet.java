package be.iepscf.refuge.business.servlet;

import be.iepscf.refuge.business.businessbean.*;
import be.iepscf.refuge.business.service.PublicService;
import be.iepscf.refuge.business.service.ServiceFactory;
import be.iepscf.refuge.business.servlet.util.Logger;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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


    public User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user instanceof User) {
                return user;
            }
        }
        return null;
    }

    public boolean hasUser(HttpServletRequest request) {
        User user = getUser(request);
        if (user instanceof User) {
            return true;
        }
        return false;

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

    protected Long getLongParameter(HttpServletRequest request, String name, Long defval) {
        String param = request.getParameter(name);
        if (param != null) {
            return Long.parseLong(param);
        }
        return defval;
    }

    protected Long getLongParameter(HttpServletRequest request, String name) {
        return getLongParameter(request, name, null);
    }

    protected Long getParameter(HttpServletRequest request, String name, Long defval) {
        return getLongParameter(request, name, defval);
    }

    protected Boolean getBooleanParameter(HttpServletRequest request, String name, Boolean defval) {
        String param = request.getParameter(name);
        if (param != null) {
            if (param.trim().equals("1")) {
                return true;
            } else {
                return Boolean.parseBoolean(param);
            }
        }
        return defval;
    }

    protected Boolean getBooleanParameter(HttpServletRequest request, String name) {
        return getBooleanParameter(request, name, null);
    }

    protected Species getSpeciesParameter(HttpServletRequest request, String name) {
        String idSpecies = request.getParameter(name);
        if (idSpecies != null) {
            Long id;
            try {
                id = Long.parseLong(idSpecies);
            } catch (NumberFormatException e) {
                id = null;
            }
            if (id != null && id != 0) {
                Species species = getPublicService().getSpecies(id);
                if (species != null) {
                    return species;
                }
            }
        }
        return null;
    }

    protected Species getSpeciesParameter(HttpServletRequest request) {
        return getSpeciesParameter(request, "species");
    }

    protected Race getRaceParameter(HttpServletRequest request, String name) {
        String idRace = request.getParameter(name);
        if (idRace != null) {
            Long id;
            try {
                id = Long.parseLong(idRace);
            } catch (NumberFormatException e) {
                id = null;
            }
            if (id != null && id != 0) {
                Race race = getPublicService().getRace(id);
                if (race != null) {
                    return race;
                }
            }
        }
        return null;
    }

    protected Race getRaceParameter(HttpServletRequest request) {
        return getRaceParameter(request, "race");
    }


    protected Animal getAnimalParameter(HttpServletRequest request, String name) {
        String idAnimal = request.getParameter(name);
        if (idAnimal != null) {
            Long id;
            try {
                id = Long.parseLong(idAnimal);
            } catch (NumberFormatException e) {
                id = null;
            }
            if (id != null && id != 0) {
                Animal item = getPublicService().getAnimal(id);
                if (item != null) {
                    return item;
                }
            }
        }
        return null;
    }








    protected String uriEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
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
