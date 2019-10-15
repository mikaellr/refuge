package be.iepscf.refuge.persistence.resource;

import be.iepscf.refuge.persistence.service.BeanService;

public abstract class BaseResource {

    BeanService beanService;

    public BeanService getBeanService() {
        if (beanService == null) {
            beanService = new BeanService();
        }
        return beanService;
    }

}
