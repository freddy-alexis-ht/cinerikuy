package com.cinerikuy.movie.controller;

import com.cinerikuy.movie.dto.MovieBillboardResponse;
import com.cinerikuy.movie.dto.MovieDetailsResponse;
import com.cinerikuy.movie.dto.MovieResponseMapper;
import com.cinerikuy.movie.entity.Movie;
import com.cinerikuy.movie.exception.BillboardException;
import com.cinerikuy.movie.exception.MovieDetailsException;
import com.cinerikuy.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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

    /** GENERAL BILLBOARD */
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

    /** SPECIFIC BILLBOARD */
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

    /** MOVIE DETAILS */
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

}
