package be.iepscf.refuge.business.service;

public class ServiceFactory {

    protected static PublicService publicService;
    protected static GestionService gestionService;

    public static PublicService getPublicService() {
        if (publicService == null) {
            publicService = new PublicService();
        }
        return publicService;
    }

    public static GestionService getGestionService() {
        if (gestionService == null) {
            gestionService = new GestionService();
        }
        return gestionService;
    }

}
