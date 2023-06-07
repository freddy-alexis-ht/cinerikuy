package com.cinerikuy.movie.service;

import com.cinerikuy.movie.entity.Genre;
import com.cinerikuy.movie.entity.Language;
import com.cinerikuy.movie.entity.Movie;
import com.cinerikuy.movie.entity.Situation;
import com.cinerikuy.movie.entity.Vote;
import com.cinerikuy.movie.repository.GenreRepository;
import com.cinerikuy.movie.repository.LanguageRepository;
import com.cinerikuy.movie.repository.MovieRepository;
import com.cinerikuy.movie.repository.SituationRepository;
import com.cinerikuy.movie.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    public MovieRepository movieRepository;
    @Autowired
    public GenreRepository genreRepository;
    @Autowired
    public LanguageRepository languageRepository;
    @Autowired
    public SituationRepository situationRepository;
    @Autowired
    public VoteRepository voteRepository;

    /** MOVIE */

    public List<Movie> movieFindAll() {
        List<Movie> list = movieRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public Movie movieFindById(long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(!movie.isPresent()) return null;
        return movie.get();
    }

    public Movie movieSaveUpdate(Movie movie) {
        return movieRepository.save(movie);
    }

    public void movieDelete(long id) {
        movieRepository.deleteById(id);
    }

    /** GENRE */

    public List<Genre> genreFindAll() {
        List<Genre> list = genreRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public Genre genreFindById(long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if(!genre.isPresent()) return null;
        return genre.get();
    }

    public Genre genreSaveUpdate(Genre genre) {
        return genreRepository.save(genre);
    }

    /** LANGUAGE */

    public List<Language> languageFindAll() {
        List<Language> list = languageRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public Language languageFindById(long id) {
        Optional<Language> language = languageRepository.findById(id);
        if(!language.isPresent()) return null;
        return language.get();
    }

    public Language languageSaveUpdate(Language language) {
        return languageRepository.save(language);
    }

    /** SITUATION */

    public List<Situation> situationFindAll() {
        List<Situation> list = situationRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public Situation situationFindById(long id) {
        Optional<Situation> situation = situationRepository.findById(id);
        if(!situation.isPresent()) return null;
        return situation.get();
    }

    public Situation situationSaveUpdate(Situation situation) {
        return situationRepository.save(situation);
    }

    /** VOTE */

    public List<Vote> voteFindAll() {
        List<Vote> list = voteRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public Vote voteFindById(long id) {
        Optional<Vote> vote = voteRepository.findById(id);
        if(!vote.isPresent()) return null;
        return vote.get();
    }

    public Vote voteSaveUpdate(Vote vote) {
        return voteRepository.save(vote);
    }

}
