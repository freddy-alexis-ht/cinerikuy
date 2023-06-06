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
import java.util.Optional;

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
    @GetMapping("/voting/{username}")
    public ResponseEntity<List<VotingListResponse>> findMoviesInVoting(@PathVariable String username) throws MovieDetailsException {
        Voting voting = votingService.findByUsername(username);

        List<Movie> movies = movieService.peruvianMovies();
        if(movies == null || movies.isEmpty())
            throw new MovieDetailsException("M005", "No hay películas peruanas en votación.", HttpStatus.PRECONDITION_FAILED);
        if(voting == null) {
            List<VotingListResponse> response = movResMapper.MovieListToVotingListResponseList(movies);
            return ResponseEntity.ok(response);
        }
        String votedMovieCode = movies.stream()
                .filter(m -> m.getId() == voting.getVotingPK().getMovieId())
                .findFirst().get()
                .getMovieCode();
        List<VotingListResponse> response = movResMapper.MovieListToVotingListResponseList(movies);
        response.stream()
                .filter(r -> r.getMovieCode() == votedMovieCode)
                .findFirst().get().setVoted(true);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Vote up for peruvian movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vote registered successfully", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error registering vote", content = @Content)})
    @PostMapping("/vote-up")
    public ResponseEntity<String> voteUpForMovie(@RequestBody VotingUpRequest request) throws MovieDetailsException {
        List<VotingListResponse> movieVoting = this.findMoviesInVoting(request.getUsername()).getBody();
        boolean hasVotes;
        Optional<VotingListResponse> any = movieVoting.stream()
                .filter(m -> m.isVoted())
                .findAny();
        if(!any.isPresent()) { // no hay votos
            if(request.getMovieCode().equals(""))
                return ResponseEntity.ok("No había voto y no voto");
            Movie movie = movieService.findByMovieCode(request.getMovieCode());
            votingService.post(movie.getId(), request.getUsername());
            return ResponseEntity.ok("No había voto y voto");
        }else{
            if(request.getMovieCode().equals(""))
                return ResponseEntity.ok("Sí había voto y no voto");
            String oldMovieCode = any.get().getMovieCode();
            String newMovieCode = request.getMovieCode();
            if(oldMovieCode.equals(newMovieCode)) {
                votingService.deleteByUsername(request.getUsername());
                return ResponseEntity.ok("Sí había voto y le quitó el voto");
            }else{
                votingService.deleteByUsername(request.getUsername());
                Movie movie = movieService.findByMovieCode(request.getMovieCode());
                votingService.post(movie.getId(), request.getUsername());
                return ResponseEntity.ok("Sí había voto y voto por otro");
            }
        }
    }

}
