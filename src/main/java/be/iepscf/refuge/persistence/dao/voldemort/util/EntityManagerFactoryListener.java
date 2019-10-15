package be.iepscf.refuge.persistence.dao.voldemort.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * closes EntityManagerFactory singleton when context is destroyed
 */
@WebListener()
public class EntityManagerFactoryListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public EntityManagerFactoryListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
    }

    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerHelper.closeEntityManagerFactory();
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

}
