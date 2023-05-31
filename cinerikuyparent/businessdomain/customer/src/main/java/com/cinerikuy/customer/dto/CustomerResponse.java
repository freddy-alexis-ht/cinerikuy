package com.cinerikuy.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CustomerResponse", description = "Object that returns username.")
public class CustomerResponse {
    private String username;
}
