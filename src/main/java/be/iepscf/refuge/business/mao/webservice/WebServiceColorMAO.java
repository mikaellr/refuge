package be.iepscf.refuge.business.mao.webservice;

import be.iepscf.refuge.business.businessbean.Color;
import be.iepscf.refuge.business.businessbean.Species;
import be.iepscf.refuge.business.mao.ColorMAO;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class WebServiceColorMAO extends WebServiceGenericMAO<Color, Long> implements ColorMAO {

    public WebServiceColorMAO() {
        entityUrl = "colors";
    }

    @Override
    public Color get(String name) {
        return getBy("name", name);
    }

    @Override
    public List<Color> get() {
        Response response =  getEntityWebTarget().request(MediaType.APPLICATION_JSON).get();
        List<Color> items = response.readEntity(new GenericType<List<Color>>() {});
        return items;
    }

}
