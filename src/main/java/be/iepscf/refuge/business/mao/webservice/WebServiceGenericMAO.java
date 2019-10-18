package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.mao.GenericMAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

public class WebServiceGenericMAO<E, ID extends Serializable> implements GenericMAO<E, ID> {

    protected String targetUrl = "https://refuge.iepscf.be/rest";

    public WebServiceGenericMAO() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("business/business.properties"));
            targetUrl = properties.getProperty("rest.url", targetUrl);
        } catch (IOException e) {
            System.err.println(e);
        }
    }






    @Override
    public E get(ID id) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return null;
    }

    @Override
    public List<E> get() {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return null;
    }

    @Override
    public long save(E entity) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return 0;
    }

    @Override
    public long update(E entity) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return 0;
    }

    @Override
    public long delete(E entity) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return 0;
    }
}
