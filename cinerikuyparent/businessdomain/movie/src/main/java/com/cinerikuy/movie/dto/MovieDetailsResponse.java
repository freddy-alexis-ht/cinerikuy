package com.cinerikuy.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "MovieDetailsResponse", description = "Object to return details of specific movie.")
public class MovieDetailsResponse {

    @Schema(name = "movieCode", example = "M001", description = "Unique movie code")
    private String movieCode;
    @Schema(name = "name", example = "Domingo Sin Fin", description = "Movie name")
    private String name;
    @Schema(name = "duration", example = "120 min", description = "Movie duration in minutes")
    private String duration;
    @Schema(name = "imageUrl", example = "http://xxx", description = "Url of the image-movie-storage")
    private String imageUrl;
    @Schema(name = "trailerUrl", example = "http://xxx", description = "Url of the trailer-movie-storage")
    private String trailerUrl;
    @Schema(name = "imageCover", example = "http://xxx", description = "Url of the image-movie-cover")
    private String imageCover;
    @Schema(name = "synopsis", example = "Resumen de película", description = "Movie synopsis (max. 250 chars)")
    private String synopsis;
    @Schema(name = "peruvian", example = "true|false", description = "Is the movie peruvian or not")
    private boolean peruvian;
    @Schema(name = "director", example = "Dir. Domingo Lucas", description = "Movie Director")
    private String director;
    @Schema(name = "actors", example = "Domingo Pitt, Lucas Brosnan", description = "Movie Director")
    private String actors;
    @Schema(name = "schedules", example = "15:30, 18:00", description = "Movie schedules")
    private List<String> schedules;
    @Schema(name = "genre", example = "Acción", description = "Movie genre")
    private String genre;
    @Schema(name = "language", example = "Español", description = "Movie language")
    private String language;
    @Schema(name = "situation", example = "Estreno", description = "Movie current situation")
    private String situation;
    @Schema(name = "vote", example = "Votación", description = "Movie is: votación, no-votación, ganador")
    private String vote;

}