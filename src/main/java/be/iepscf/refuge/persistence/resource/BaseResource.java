package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.service.BeanService;

import javax.ws.rs.core.Response;

public abstract class BaseResource {

    BeanService beanService;

    public BeanService getBeanService() {
        if (beanService == null) {
            beanService = new BeanService();
        }
        return beanService;
    }

    public Response notFound() {
        return Response.status(404).entity("Resource not found").build();
    }

}
