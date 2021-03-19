package cloud.nativ.jakarta;

import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static java.util.Collections.singletonMap;

@Log
@ApplicationScoped
@Path("demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {

    @Inject
    private DemoService service;

    @GET
    public Response demo(@QueryParam("name") String name) {


        Map<String, Object> payload = singletonMap("name", service.getMessage(name));
        return Response.ok(payload).build();
    }
}
