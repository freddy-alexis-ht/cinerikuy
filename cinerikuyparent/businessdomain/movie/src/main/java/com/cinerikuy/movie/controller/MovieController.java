package com.cinerikuy.movie.controller;

import com.cinerikuy.movie.dto.*;
import com.cinerikuy.movie.entity.Movie;
import com.cinerikuy.movie.entity.Voting;
import com.cinerikuy.movie.entity.VotingPK;
import com.cinerikuy.movie.exception.BillboardException;
import com.cinerikuy.movie.exception.MovieDetailsException;
import com.cinerikuy.movie.service.MovieService;
import com.cinerikuy.movie.service.VotingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieResponseMapper movResMapper;
    @Autowired
    private VotingService votingService;

    @Operation(summary = "Get the main billboard (no matter the cinema).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Main billboard returned", content = @Content),
            @ApiResponse(responseCode = "412", description = "There are no movies in Billboard", content = @Content)})
    @GetMapping("/billboard")
    public ResponseEntity<List<MovieBillboardResponse>> getMainBillboard() throws BillboardException {
        List<Movie> list = movieService.getMainBillboard();
        if(list == null || list.isEmpty())
            throw new BillboardException("M001", "No hay películas de estreno.", HttpStatus.PRECONDITION_FAILED);
        List<MovieBillboardResponse> response = movResMapper.MovieListToMovieBillboardResponseList(list);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get billboard of specific cinema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Main billboard returned", content = @Content),
            @ApiResponse(responseCode = "412", description = "There are no movies in Billboard in this cinema", content = @Content)})
    @GetMapping("/billboard/{cinemaCode}")
    public ResponseEntity<List<MovieBillboardResponse>> getSpecificBillboard(@PathVariable String cinemaCode) throws BillboardException {
        List<Movie> list = movieService.getSpecificBillboard(cinemaCode);
        if(list == null || list.isEmpty())
            throw new BillboardException("M002", "No hay películas de estreno en este cine.", HttpStatus.PRECONDITION_FAILED);
        List<MovieBillboardResponse> response = movResMapper.MovieListToMovieBillboardResponseList(list);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get movie details by movie-code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie details returned", content = @Content),
            @ApiResponse(responseCode = "412", description = "Movie doesn't exist", content = @Content)})
    @GetMapping("/{movieCode}/details")
    public ResponseEntity<MovieDetailsResponse> getMovieDetails(@PathVariable String movieCode) throws MovieDetailsException {
        Movie movie = movieService.getMovieDetails(movieCode);
        if(movie == null)
            throw new MovieDetailsException("M003", "La película indicada no existe.", HttpStatus.PRECONDITION_FAILED);
        MovieDetailsResponse response = movResMapper.MovieToMovieDetailsResponse(movie);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get movie by movie-code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie returned successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "Movie doesn't exist", content = @Content)})
    @GetMapping("/movieCode/{movieCode}")
    public ResponseEntity<Movie> findByMovieCode(@PathVariable String movieCode) throws MovieDetailsException {
        Movie movie = movieService.findByMovieCode(movieCode);
        if(movie == null)
            throw new MovieDetailsException("M004", "MovieCode no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(movie);
    }

    @Operation(summary = "Get peruvian movies in voting period.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peruvian movies returned successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "There are not peruvian movies in voting", content = @Content)})
    @GetMapping("/voting")
    public ResponseEntity<List<VotingListResponse>> findMoviesInVoting() throws MovieDetailsException {
        List<Movie> movies = movieService.peruvianMovies();
        if(movies == null || movies.isEmpty())
            throw new MovieDetailsException("M005", "No hay películas peruanas en votación.", HttpStatus.PRECONDITION_FAILED);
        List<VotingListResponse> response = movResMapper.MovieListToVotingListResponseList(movies);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Vote up for peruvian movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vote registered successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error registering vote", content = @Content)})
    @PostMapping("/vote-up")
    public ResponseEntity<String> voteUpForMovie(@RequestBody VotingUpRequest request) throws MovieDetailsException {
        Movie movie = movieService.findByMovieCode(request.getMovieCode());
        if(movie == null)
            throw new MovieDetailsException("M006", "No hay películas con ese movie-code.", HttpStatus.PRECONDITION_FAILED);
        try{
            votingService.post(movie.getId(), request.getUsername());
        }catch (DataIntegrityViolationException e) {
            throw new MovieDetailsException("M007", "El usuario no puede votar por 2 películas, solo 1.", HttpStatus.PRECONDITION_FAILED);
        }
        return ResponseEntity.ok("Éxito");
    }

}
