package com.cinerikuy.movie.dto;

import com.cinerikuy.movie.entity.Movie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieResponseMapper {

    // MAPPERS FOR MAIN-BILLBOARD
    @Mappings({
            @Mapping(source = "genre.genre", target = "genre")
    })
    MovieMainBillboardResponse MovieToMovieMainBillboardResponse(Movie source);

    @InheritInverseConfiguration
    Movie MovieMainBillboardResponseToMovie(MovieMainBillboardResponse source);

    List<MovieMainBillboardResponse> MovieListToMovieMainBillboardResponseList(List<Movie> source);

    // MAPPERS FOR MOVIE-DETAILS
    @Mappings({
            @Mapping(source = "genre.genre", target = "genre"),
            @Mapping(source = "language.language", target = "language"),
            @Mapping(source = "situation.situation", target = "situation"),
            @Mapping(source = "vote.vote", target = "vote")
    })
    MovieDetailsResponse MovieToMovieDetailsResponse(Movie source);

}