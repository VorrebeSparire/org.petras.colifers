package org.petras.colifers.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.petras.colifers.util.error.ErrorResponse;
import org.petras.colifers.util.error.MicroserviceException;

@Path("/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TestResource {

    @GET
    @Path("/hello-world/{id}")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "418", description = "I am a tea pot", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response test(@PathParam(value = "id") int id) throws MicroserviceException {
        if (id == 0) {
            throw new MicroserviceException("hello-world", "hello-world", 418);
        } else {
            throw new RuntimeException("smth went wrong");
        }
    }
}
