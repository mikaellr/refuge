package be.iepscf.refuge.business.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

    /* récupère contenu textuel d'une URL, si besoin pour requètes artisanales sans framework */
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
