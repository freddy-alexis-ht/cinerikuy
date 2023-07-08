package com.cinerikuy.movie.dto;

import com.cinerikuy.movie.entity.Movie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieResponseMapper {

    // MAPPERS FOR BILLBOARD
    @Mappings({
            @Mapping(source = "genre.genre", target = "genre")
    })
    MovieBillboardResponse MovieToMovieBillboardResponse(Movie source);

    @InheritInverseConfiguration
    Movie MovieBillboardResponseToMovie(MovieBillboardResponse source);

    List<MovieBillboardResponse> MovieListToMovieBillboardResponseList(List<Movie> source);

    // MAPPERS FOR MOVIE-DETAILS
    @Mappings({
            @Mapping(source = "genre.genre", target = "genre"),
            @Mapping(source = "language.language", target = "language"),
            @Mapping(source = "situation.situation", target = "situation"),
            @Mapping(source = "vote.vote", target = "vote"),
            @Mapping(source = "imageCover", target = "imageCover")
    })
    MovieDetailsResponse MovieToMovieDetailsResponse(Movie source);

    // MAPPERS FOR VOTING
    @Mappings({
            @Mapping(source = "language.language", target = "language"),
            @Mapping(source = "genre.genre", target = "genre")
    })
    VotingListResponse MovieToVotingListResponse(Movie source);

    List<VotingListResponse> MovieListToVotingListResponseList(List<Movie> source);

}