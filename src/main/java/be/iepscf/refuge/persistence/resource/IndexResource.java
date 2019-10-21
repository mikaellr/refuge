package be.iepscf.refuge.persistence.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class IndexResource extends BaseResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response sayHello() {
        String baseUrl = "/refuge/rest/";
        String urls[] = {
                "users",
                "users/12",
                "animals",
                "animals/4",
                "species",
                "species/2",
                "species/2/races",
                "races/45",
                "colors",
                "colors/17",
                "roles",
                "roles/1",
                "contact-requests",
                "contact-requests/4",
        };
        String output = "<h1>Annuaire des resources Rest<h1><ul>";
        for (String url : urls) {
            output += "<li><a href=\"" + baseUrl + url + "\">" + url + "</a></li>";

        }
        output += "</ul>";
        return Response.status(200).entity(output).build();
    }

}
