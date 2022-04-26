package cloud.nativ.jakarta;

import cloud.nativ.jakarta.dto.GenericRequest;
import cloud.nativ.jakarta.web.WebClient;
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
    private WebClient webClient;

    @GET
    public Response demo(final GenericRequest request) {
        return Response.ok(webClient.handleRequest(request.getId())).build();
    }
}
