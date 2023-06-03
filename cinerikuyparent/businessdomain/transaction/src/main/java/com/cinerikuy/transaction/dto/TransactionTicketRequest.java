package com.cinerikuy.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TransactionTicketRequest", description = "Data sent to buy tickets.")
public class TransactionTicketRequest {

    @Schema(name = "username", example = "Domingo7", description = "Unique customer username")
    private String username;
    @Schema(name = "cinemaCode", example = "C01", description = "Unique cinema code")
    private String cinemaCode;
    @Schema(name = "movieCode", example = "M001", description = "Unique movie code")
    private String movieCode;
    @Schema(name = "movieSchedule", example = "15:30", description = "Movie schedule")
    private String movieSchedule;
    @Schema(name = "movieNumberOfTickets", example = "2", description = "Number of tickets to buy")
    private int movieNumberOfTickets;
}
