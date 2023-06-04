package com.cinerikuy.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// TODO .. Cambiar de nombre a 'TransactionCodeResponse' y usarlo en los métodos buyTickets() y buyProducts() del controlador
@Data
@Schema(name = "TransactionTicketResponse", description = "Confirmation data after transaction storing.")
public class TransactionTicketResponse {

    @Schema(name = "transactionCode", example = "12k1jl21", description = "Unique transaction code randomly generated")
    private String transactionCode;
    @Schema(name = "productsTotalPrice", example = "S/ 24.00", description = "Total billing for tickets only")
    private String ticketsTotalPrice;

}
