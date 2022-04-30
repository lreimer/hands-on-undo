package hands.on.undo.parts;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collection;

@ApplicationScoped
@Path("parts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PartsResource {

    @Inject
    private PartsRepository repository;

    @GET
    public Response getParts(@QueryParam("vin17") String vin17) {
        Collection<Part> parts = repository.findParts(vin17);
        return Response.ok(parts).build();
    }
}
