package com.cinerikuy.cinema.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CinemaResponse", description = "Object that returns Cinema information.")
public class CinemaResponse {

    @Schema(name = "cinemaCode", example = "C01", description = "Unique cinema code")
    private String cinemaCode;
    @Schema(name = "name", example = "CR Centro Cívico", description = "Cinema name")
    private String name;
    @Schema(name = "address", example = "Av. Domingo 123", description = "Cinema address")
    private String address;
    @Schema(name = "district", example = "San Borja", description = "Cinema district")
    private String district;
    @Schema(name = "city", example = "Lima", description = "Cinema city")
    private String city;

}
