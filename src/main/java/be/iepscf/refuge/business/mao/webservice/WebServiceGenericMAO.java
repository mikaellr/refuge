package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.mao.GenericMAO;
import be.iepscf.refuge.business.util.Logger;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
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
    private Class<ID> keyClass;

    public WebServiceGenericMAO() {
        entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        keyClass = (Class<ID>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

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

    protected Class<ID> getKeyClass() {
        return keyClass;
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
            debug("warning : entityUrl not set (" + getClass().getName() + ")");
        }
        return getWebTarget().path(entityUrl);
    }


    /**
     * ok, works
     *
     * requête de type: /<target>/<entity>/{id} => E
     *
     * @param id
     * @return
     */
    @Override
    public E get(ID id) {
        debug("wsc generic get : id = " + id);
        Response resp = getEntityWebTarget().path(id.toString()).request(MediaType.APPLICATION_JSON).get();
        if (resp.getStatus() == 200) {
            E item = resp.readEntity(getEntityClass());
            return item;
        }
        return null;
    }

    /**
     * ok, works
     *
     * requête de type: /<target>/<entity>/key/{value} => E
     *
     */
    public E getBy(String key, Object value) {
        Response response = getEntityWebTarget().path(key).path(value.toString()).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
            E item = response.readEntity(getEntityClass());
            return item;
        }
        return null;
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


    // WebTarget helloworldWebTargetWithQueryParam =                webTarget.queryParam("greeting", "Hi World!");


    public long save(E entity, boolean readEntity) {
//        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
//        return -1;
        debug("wsc generic save (entity= " + getEntityName() + ")");
        Response response = getEntityWebTarget().request(MediaType.APPLICATION_JSON).post(Entity.entity(entity, MediaType.APPLICATION_JSON));
        debug("wsc generic save : status = " + response.getStatus());
        if (readEntity) {
            E res = response.readEntity(getEntityClass());
            debug("wsc generic save : res = " + res);
        } else {
            debug("wsc generic save : res = " + response.readEntity(String.class));
        }
        if (response.getStatus() == 201) {
            return 1;
        }
        return -1;
    }

    @Override
    public long save(E entity) {
        return save(entity, true);
    }


    @Override
    public long update(E entity) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return -1;
    }

    public long update(E entity, String id, boolean readEntity) {
        debug("wsc generic update (entity= " + getEntityName() + ", id=" + id + ")");
        Response response = getEntityWebTarget().path(id).request(MediaType.APPLICATION_JSON).put(Entity.entity(entity, MediaType.APPLICATION_JSON));
        debug("wsc generic update : status = " + response.getStatus());
        if (readEntity) {
            E res = response.readEntity(getEntityClass());
            debug("wsc generic update : res = " + res);
        } else {
            debug("wsc generic update : res = " + response.readEntity(String.class));
        }
        if (response.getStatus() == 200) {
            return 1;
        }
        return -1;
    }

    public long update(E entity, String id) {
        return update(entity, id, true);
    }

    @Override
    public long delete(E entity) {
        System.err.println(String.format("Not implemented method in %s derived class", getClass().getName()));
        return -1;
    }

    public long delete(E entity, String id) {
        Response response = getEntityWebTarget().path(id).request(MediaType.APPLICATION_JSON).delete();
        debug("wsc generic delete : status = " + response.getStatus());
        debug("wsc generic delete : response = " + response.readEntity(getEntityClass()));
        if (response.getStatus() == 200) {
            return 1;
        }
        return -1;
    }

}
