package com.cinerikuy.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@Schema(name = "VotingListResponse", description = "Return voting results.")
public class VotingResultResponse {

    @Schema(name = "namesAndNumber", example = "{\"Domingo en Pucallpa\":3, \"Lucas en Lima\":2}", description = "Movie names and number")
    private Map<String, Long> namesAndNumber;

    @Schema(name = "totalVotes", example = "12", description = "The sum of all votes")
    private long totalVotes;

}