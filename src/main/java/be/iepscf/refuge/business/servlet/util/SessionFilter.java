package be.iepscf.refuge.business.servlet.util;

import be.iepscf.refuge.business.businessbean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter", urlPatterns = "/gestion/*")
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        if (session != null) {   //checking whether the session exists
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                Logger.getLogger().debug("session filter : user=" + user);
                chain.doFilter(req, resp);
                return;
            } else {
                Logger.getLogger().debug("session filter : no user");
            }
        } else {
            Logger.getLogger().debug("session filter : no session");
        }
        String location = request.getContextPath() + "/login";
        Logger.getLogger().debug("session filter : redirecting to location=" + location);
        response.sendRedirect(location);
    }

}
