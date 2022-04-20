package cloud.nativ.jakarta;

import io.undo.lr.APICallFailed;
import io.undo.lr.NoAttachYama;
import io.undo.lr.RecordInProgress;
import io.undo.lr.UndoLR;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.util.logging.Level;

@Log
@ApplicationScoped
@Path("lr4j")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LR4JResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    @ConfigProperty(name = "lr4j.directory", defaultValue = "/tmp/")
    private String lr4jDirectory;

    @POST
    public Response start() {
        try {
            LOGGER.info("Starting LR4J recording.");
            UndoLR.start();
        } catch (RecordInProgress recordInProgress) {
            LOGGER.log(Level.WARNING, "LR4J recording in progress.", recordInProgress);
            return Response.status(Response.Status.CONFLICT).build();
        } catch (NoAttachYama noAttachYama) {
            LOGGER.log(Level.SEVERE, "LR4J attachment error.", noAttachYama);
            return Response.serverError().build();
        } catch (APICallFailed apiCallFailed) {
            LOGGER.log(Level.SEVERE, "LR4J API call failed.", apiCallFailed);
            return Response.serverError().build();
        }
        return Response.noContent().build();
    }

    @DELETE
    public Response stop() {
        try {
            LOGGER.info("Stopping LR4J recording.");
            UndoLR.stop();
        } catch (APICallFailed apiCallFailed) {
            LOGGER.log(Level.SEVERE, "LR4J API call failed.", apiCallFailed);
            return Response.serverError().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/{filename}")
    public Response save(@PathParam("filename") @NotBlank String filename) {
        try {
            LOGGER.log(Level.INFO, "Saving LR4J recording to {0}", filename);
            UndoLR.save(getFilename(filename));
        } catch (APICallFailed apiCallFailed) {
            LOGGER.log(Level.SEVERE, "LR4J API call failed.", apiCallFailed);
            return Response.serverError().build();
        }

        URI location = uriInfo.getBaseUriBuilder().path(LR4JResource.class).path(filename).build();
        return Response.created(location).build();
    }

    @GET
    @Path("/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response get(@PathParam("filename") @NotBlank String filename) {
        LOGGER.log(Level.INFO, "Reading LR4J recording {0}", filename);
        return Response.ok(new File(getFilename(filename)))
                .header("Content-Disposition", "attachment; filename=" + filename)
                .build();
    }

    private String getFilename(String filename) {
        return Paths.get(lr4jDirectory, filename).toAbsolutePath().toString();
    }
}
