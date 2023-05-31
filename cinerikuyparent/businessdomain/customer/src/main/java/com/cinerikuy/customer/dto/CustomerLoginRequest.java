package com.cinerikuy.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CustomerLoginRequest", description = "Object that includes fields to login.")
public class CustomerLoginRequest {
    @Schema(name = "username", required = true, example = "Domingo7", description = "Unique customer username")
    private String username;
    @Schema(name = "password", required = true, example = "domingo", description = "Account password")
    private String password;
}
