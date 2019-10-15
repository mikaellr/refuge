package be.iepscf.refuge.persistence.dao.voldemort.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *
 * "session per request" pattern : one EntityManager instance per servlet request / thread
 * - created (possibly) during request process as a thread-local singleton by calling EntityManagerHelper.getEntityManager()
 * - closed here (if necessary) at the end of request process by calling EntityManagerHelper.close()
 *
 * "open session in view" : session closed here after result (jsp...) rendering to allow "lazy loading"
 */
@WebFilter(filterName = "OpenEntityManagerInViewFilter")
public class OpenEntityManagerInViewFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        EntityManagerHelper.close();
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
