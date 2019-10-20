package be.iepscf.refuge.business.servlet.gestion;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.iepscf.refuge.business.businessbean.Animal;
import be.iepscf.refuge.business.businessbean.Color;
import be.iepscf.refuge.business.businessbean.Race;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.service.GestionService;
import be.iepscf.refuge.business.servlet.gestion.GestionServlet;


@WebServlet(name ="AnimalsServlet", urlPatterns ={"/gestion/animals"})
public class AnimalsAddServlet extends GestionServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int birthYear = Integer.parseInt(request.getParameter("birthYear"));

        String sex = request.getParameter("sex");

        Long id = getParameter(request, "id");
        boolean sterilized = Boolean.parseBoolean(request.getParameter("sterilized"));
        boolean adoptable = Boolean.parseBoolean(request.getParameter("adoptable"));
        String photoContentType= request.getParameter("photoContentType");
        int photoContentLength= Integer.parseInt(request.getParameter("photoContentLength"));
        String photoContent= request.getParameter("photoContent");
        String species= request.getParameter("species");
        String race= request.getParameter("race");
        String color= request.getParameter("color");

        Animal animal = getGestionService().addAnimal(
                name,
                description,
                birthYear,
                sex,
                sterilized,
                adoptable,
                photoContentType,
                photoContentLength,
                photoContent,
                species,
                race,
                color);
        sendRedirect(response, "/gestion/animals");
    }

}
