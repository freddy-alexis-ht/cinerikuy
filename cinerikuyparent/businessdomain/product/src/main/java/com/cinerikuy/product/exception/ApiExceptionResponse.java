package com.cinerikuy.product.exception;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
    Standardizing rest API error reports
*/

@Getter @Setter @AllArgsConstructor
@Schema(name = "ApiExceptionResponse", description = "Error description returned to client.")
public class ApiExceptionResponse {

    @Schema(name = "type", description = "Error general information",
            required = true, example = "Form validation error")
    private String type;
    @Schema(name = "code", description = "Unique business error code",
            required = true, example = "F001")
    private String code;
    @Schema(name = "detail", description = "A human-readable explanation of the error",
            required = true, example = "The user does not have the proper permissions")
    private String detail;
    @Schema(name = "httpStatus", description = "HTTP status code and message",
            required = true, example = "412 PRECONDITION_FAILED")
    private String httpStatus;

}
