package org.petras.colifers.util.error;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MicroserviceExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        ErrorResponse error = new ErrorResponse();

        if (throwable instanceof MicroserviceException microserviceException) {

            error.errorCode = microserviceException.errorCode;
            error.message = microserviceException.getMessage();
            error.statusCode = microserviceException.statusCode;


        } else {
            MicroserviceException wrappedException = new MicroserviceException("UNEXPECTED_ERROR", throwable.getMessage(), 500, throwable);

            error.errorCode = wrappedException.errorCode;
            error.message = wrappedException.getMessage();
            error.statusCode = wrappedException.statusCode;
        }

        return Response.status(error.statusCode).entity(error).build();
    }
}
