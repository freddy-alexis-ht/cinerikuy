package com.cinerikuy.movie.service;

import com.cinerikuy.movie.entity.Movie;
import com.cinerikuy.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllBillboard() {
        return movieRepository.findAll().stream()
                .filter(m -> m.isEnabled())
                .filter(m -> m.getSituation().getSituation().equals("Estreno"))
                .collect(Collectors.toList());
    }

    public Movie getMovieDetails(String movieCode) {
        Optional<Movie> findByMovieCode = movieRepository.findByMovieCode(movieCode);
        if(!findByMovieCode.isPresent())
            return null;
        Movie movie = findByMovieCode.get();
        if(!movie.isEnabled())
            return null;
        return movie;
    }

}
