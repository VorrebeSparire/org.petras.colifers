package org.petras.colifers.util.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "ErrorResponse", description = "API error response body.")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    @Schema(
            description = "Microservices error code.",
            readOnly = true,
            required = true,
            example = "RESOURCE_NOT_FOUND"
    )
    @JsonProperty("error-code")
    public String errorCode;

    @Schema(
            description = "Microservices error description.",
            readOnly = true,
            required = true,
            example = "Could not find User with Id: 0"
    )
    @JsonProperty("error-description")
    public String message;

    @Schema(
            description = "HTTP status code from the response.",
            readOnly = true,
            required = true,
            example = "404"
    )
    @JsonProperty("status-code")
    public int statusCode;

    public ErrorResponse() {
        //reflection
    }
}
