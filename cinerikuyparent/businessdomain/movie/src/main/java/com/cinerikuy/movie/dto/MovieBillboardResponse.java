package com.cinerikuy.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "MovieBillboardResponse", description = "Object to return current billboard movies, no matter the cinema.")
public class MovieBillboardResponse {

    @Schema(name = "movieCode", example = "M001", description = "Unique movie code")
    private String movieCode;
    @Schema(name = "name", example = "Domingo Sin Fin", description = "Movie name")
    private String name;
    @Schema(name = "duration", example = "120 min", description = "Movie duration in minutes")
    private String duration;
    @Schema(name = "imageUrl", example = "http://xxx", description = "Url of the image-movie-storage")
    private String imageUrl;
    @Schema(name = "genre", example = "Acción", description = "Movie genre")
    private String genre;

}