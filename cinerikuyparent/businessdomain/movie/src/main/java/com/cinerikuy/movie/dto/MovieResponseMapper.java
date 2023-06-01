package com.cinerikuy.movie.dto;

import com.cinerikuy.movie.entity.Movie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieResponseMapper {

    @Mappings({
            @Mapping(source = "genre.genre", target = "genre")
    })
    MovieMainBillboardResponse MovieToMovieMainBillboardResponse(Movie source);

    @InheritInverseConfiguration
    Movie MovieMainBillboardResponseToMovie(MovieMainBillboardResponse source);

    List<MovieMainBillboardResponse> MovieListToMovieMainBillboardResponseList(List<Movie> source);
}