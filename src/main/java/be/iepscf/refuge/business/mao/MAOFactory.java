package be.iepscf.refuge.business.mao;

import be.iepscf.refuge.business.mao.webservice.WebServiceMAOFactory;

public abstract class MAOFactory {

    private static Class<WebServiceMAOFactory> defaultMAOFactoryClass = WebServiceMAOFactory.class;

    public static MAOFactory getMAOFactory() {
        return getMAOFactory(defaultMAOFactoryClass);
    }

    public static MAOFactory getMAOFactory(Class factory) {
        try {
            return (MAOFactory) factory.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create MAOFactory: " + factory);
        }
    }

    public abstract AnimalMAO getAnimalMAO();
    public abstract ColorMAO getColorMAO();
    public abstract ContactRequestMAO getContactRequestMAO();
    public abstract RaceMAO getRaceMAO();
    public abstract RoleMAO getRoleMAO();
    public abstract SpeciesMAO getSpeciesMAO();
    public abstract UserMAO getUserMAO();

}
