package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.mao.GenericMAO;
import be.iepscf.refuge.business.servlet.util.Logger;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Properties;

public abstract class WebServiceGenericMAO<E, ID extends Serializable> implements GenericMAO<E, ID> {

    protected String targetUrl = "https://refuge.iepscf.be/rest";
    protected String entityUrl = "";
    private Class<E> entityClass;

    public WebServiceGenericMAO() {
        entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("business/business.properties"));
            targetUrl = properties.getProperty("rest.url", targetUrl);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    protected Class<E> getEntityClass() {
        return entityClass;
    }

    protected String getEntityName() {
        return getEntityClass().getSimpleName();
    }


    protected void debug(String msg) {
        Logger.getLogger().debug(msg);
    }




    protected WebTarget getWebTarget() {
        Client client = ClientBuilder.newClient( new ClientConfig());
        WebTarget webTarget = client.target(targetUrl);
        return webTarget;
    }

    protected WebTarget getEntityWebTarget() {
        if (entityUrl == null || entityUrl.equals("")){
            debug("warning : entityUrl not set");
        }
        return getWebTarget().path(entityUrl);
    }


    /**
     * ok, works
     *
     * @param id
     * @return
     */
    @Override
    public E get(ID id) {
        Response resp = getEntityWebTarget().path(id.toString()).request(MediaType.APPLICATION_JSON).get();
        E item = resp.readEntity(getEntityClass());
        return item;
    }

    /**
     * attempt to generalize, doesn't work...
     * "java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to ... <E>"
     *
     * @return
     */
    @Override
    public List<E> get() {
        Response response = getEntityWebTarget().request(MediaType.APPLICATION_JSON).get();
        List<E> items = response.readEntity(new GenericType<List<E>>() {});
        return items;
    }

    @Override
    public long save(E entity) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return -1;
    }

    @Override
    public long update(E entity) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return -1;
    }

    @Override
    public long delete(E entity) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return -1;
    }

}
