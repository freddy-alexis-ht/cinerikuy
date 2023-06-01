package com.cinerikuy.movie.controller;

import com.cinerikuy.movie.dto.MovieMainBillboardResponse;
import com.cinerikuy.movie.dto.MovieResponseMapper;
import com.cinerikuy.movie.entity.Movie;
import com.cinerikuy.movie.exception.MainBillboardException;
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
            @ApiResponse(responseCode = "204", description = "There are no movies in Billboard", content = @Content)})
    @GetMapping("/billboard")
    public ResponseEntity<List<MovieMainBillboardResponse>> getAllBillboard() throws MainBillboardException {
        List<Movie> list = movieService.getAllBillboard();
        if(list == null || list.isEmpty())
            throw new MainBillboardException("M001", "No hay películas de estreno.", HttpStatus.NO_CONTENT);
        List<MovieMainBillboardResponse> response = movResMapper.MovieListToMovieMainBillboardResponseList(list);
        return ResponseEntity.ok(response);
    }


}
