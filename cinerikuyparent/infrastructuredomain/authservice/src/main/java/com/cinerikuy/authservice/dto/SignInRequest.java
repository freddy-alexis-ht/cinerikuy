package com.cinerikuy.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// TODO .. add validation and patterns in schema and use @Valid
@Data
@Schema(name = "SignInRequest", description = "Object that includes fields to create an account.")
public class SignInRequest {
    @Schema(name = "username", required = true, example = "Domingo7", description = "Unique customer username")
    private String username;
    @Schema(name = "password", required = true, example = "domingo", description = "Account password")
    private String password;
    @Schema(name = "firstName", required = true, example = "Domingo", description = "Customer first name")
    private String firstName;
    @Schema(name = "lastName", required = true, example = "Negro", description = "Customer last name")
    private String lastName;
    @Schema(name = "dni", required = true, example = "12345678", description = "Unique customer identification")
    private String dni;
    @Schema(name = "cellphone", required = true, example = "987654321", description = "Customer cellphone")
    private String cellphone;
}
