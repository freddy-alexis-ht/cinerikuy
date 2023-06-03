package com.cinerikuy.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TransactionTicketResponse", description = "Confirmation data after transaction storing.")
public class TransactionTicketResponse {

    @Schema(name = "transactionCode", example = "12k1jl21", description = "Unique transaction code randomly generated")
    private String transactionCode;
    @Schema(name = "ticketsTotalPrice", example = "S/ 24.00", description = "Total billing for tickets only")
    private String ticketsTotalPrice;

    // private double productsTotalPrice;
    // private double billingTotalPrice;
}
