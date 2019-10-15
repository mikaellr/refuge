package be.iepscf.refuge.business.service;

public class ServiceFactory {

    protected static PublicService publicService;
    protected static EmployeService employeService;

    public static PublicService getPublicService() {
        if (publicService == null) {
            publicService = new PublicService();
        }
        return publicService;
    }

    public static EmployeService getEmployeService() {
        if (employeService == null) {
            employeService = new EmployeService();
        }
        return employeService;
    }

}
