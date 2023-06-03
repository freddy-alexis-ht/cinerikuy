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

    public List<Movie> getMainBillboard() {
        return movieRepository.findAll().stream()
                .filter(m -> m.isEnabled())
                .filter(m -> m.getSituation().getSituation().equals("Estreno"))
                .collect(Collectors.toList());
    }

    public List<Movie> getSpecificBillboard(String cinemaCode) {
        List<Movie> list = this.getMainBillboard();
        if(list == null || list.isEmpty())
            return null;
        return list.stream()
                .filter(m -> m.getCinemaCodes().contains(cinemaCode))
                .collect(Collectors.toList());
    }

    public Movie getMovieDetails(String movieCode) {
        Movie movie = this.findByMovieCode(movieCode);
        if(movie == null) return null;
        return movie;
    }

    public Movie findByMovieCode(String movieCode) {
        Optional<Movie> movie = movieRepository.findByMovieCode(movieCode);
        if(!movie.isPresent()) return null;
        if(!movie.get().isEnabled()) return null;
        return movie.get();
    }

}
