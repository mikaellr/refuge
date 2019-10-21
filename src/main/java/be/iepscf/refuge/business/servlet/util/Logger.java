package be.iepscf.refuge.business.servlet.util;

public class Logger {

    public static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void debug(String message) {
        System.out.println("<debug> " + message);
    }

}
