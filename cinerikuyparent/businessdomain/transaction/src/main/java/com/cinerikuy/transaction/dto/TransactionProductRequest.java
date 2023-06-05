package com.cinerikuy.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;

@Data
@Schema(name = "TransactionProductRequest", description = "Product transaction information.")
public class TransactionProductRequest {

    @Schema(name = "username", example = "Domingo7", description = "Unique customer username")
    private String username;
    @Schema(name = "mapCodeAmount", example = "{\"P01\":3, \"P02\":2}", description = "Map of productCode and amount")
    private HashMap<String,Integer> mapCodeAmount;
    @Schema(name = "cinemaCode", example = "C01", description = "Unique cinema code")
    private String cinemaCode;
}