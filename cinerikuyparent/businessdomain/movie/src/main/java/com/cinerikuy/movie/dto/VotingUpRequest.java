package com.cinerikuy.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "VotingUpRequest", description = "Object to send movie to vote-up.")
public class VotingUpRequest {

    @Schema(name = "movieCode", example = "M001", description = "Unique movie code")
    private String movieCode;
    @Schema(name = "username", example = "Domingo7", description = "Unique customer username")
    private String username;
}