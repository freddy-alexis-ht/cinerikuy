package com.cinerikuy.movie.entity;

import com.cinerikuy.movie.service.StringToListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "cr_movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String movieCode;
    private String name;
    private String duration;
    private String imageUrl;
    private String trailerUrl;
    private String synopsis;
    private boolean peruvian;
    private String director;
    private String actors;
    @Convert(converter = StringToListConverter.class)
    private List<String> schedules;
    @ManyToOne
    private Genre genre;
    @OneToOne
    private Language language;
    @OneToOne
    private Situation situation;
    @OneToOne
    private Vote vote;
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Collection<Voting> votings = new ArrayList<>();
    private boolean enabled;
    @Convert(converter = StringToListConverter.class)
    private List<String> cinemaCodes;
}
