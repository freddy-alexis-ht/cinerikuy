package com.cinerikuy.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "VotingListResponse", description = "Object to return peruvian movie in voting.")
public class VotingListResponse {

    @Schema(name = "movieCode", example = "M001", description = "Unique movie code")
    private String movieCode;
    @Schema(name = "name", example = "Domingo Sin Fin", description = "Movie name")
    private String name;
    @Schema(name = "duration", example = "120 min", description = "Movie duration in minutes")
    private String duration;
    @Schema(name = "imageUrl", example = "http://xxx", description = "Url of the image-movie-storage")
    private String imageUrl;
    @Schema(name = "synopsis", example = "Resumen de película", description = "Movie synopsis (max. 250 chars)")
    private String synopsis;
    @Schema(name = "director", example = "Dir. Domingo Lucas", description = "Movie Director")
    private String director;
    @Schema(name = "actors", example = "Domingo Pitt, Lucas Brosnan", description = "Movie Director")
    private String actors;
    @Schema(name = "genre", example = "Acción", description = "Movie genre")
    private String genre;
    @Schema(name = "language", example = "Español", description = "Movie language")
    private String language;
    @Schema(name = "voted", example = "true|false", description = "Movie with a vote registered")
    private boolean voted = false;
}