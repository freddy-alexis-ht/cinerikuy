package com.cinerikuy.cinema.dto;

import com.cinerikuy.cinema.entity.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CinemaResponseMapper {

    // MAPPERS FOR ALL CINEMAS
    @Mappings({
            @Mapping(source = "city.city", target = "city"),
            @Mapping(source = "ticketPrice", target = "ticketPrice")
//            @Mapping(source = "ticketPrice", target = "ticketPrice", numberFormat = "S/ #.00")
    })

    CinemaResponse CinemaToCinemaResponse(Cinema cinema);

    List<CinemaResponse> CinemaListToCinemaResponseList(List<Cinema> source);

}