package com.cinerikuy.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// TODO .. Crear 'TransactionCodeResponse' y usarlo en los métodos buyTickets() y buyProducts() del controlador
@Data
@Schema(name = "TransactionBillingResponse", description = "Confirmation data after transaction payment.")
public class TransactionBillingResponse {

    @Schema(name = "transactionCode", example = "12k1jl21", description = "Unique transaction code randomly generated")
    private String transactionCode;
    @Schema(name = "cinemaName", example = "CR NombreCine", description = "Cinema name")
    private String cinemaName;
    @Schema(name = "movieName", example = "El agente Domingo", description = "Movie name")
    private String movieName;
    @Schema(name = "movieSchedule", example = "20:30", description = "Movie schedule")
    private String movieSchedule;
    @Schema(name = "date", example = "2022-12-31 23.59.59", description = "Billing date")
    private String date;
    @Schema(name = "totalCost", example = "S/ 24.00", description = "Total billing cost")
    private String totalCost;

}
