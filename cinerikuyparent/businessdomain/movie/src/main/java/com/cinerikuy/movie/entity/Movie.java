package com.cinerikuy.movie.entity;

import com.cinerikuy.movie.service.StringToListConverter;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String movieCode;
    private String name;
    @ManyToOne
    private Genre genre;
    private String duration;
    private String imageUrl;
    private String trailerUrl;
    private String synopsis;
    private boolean peruvian;
    @OneToOne
    private Situation situation;
    @OneToOne
    private Vote vote;
    private String director;
    private String actors;
    @OneToOne
    private Language language;
    @Convert(converter = StringToListConverter.class)
    private List<String> schedules;
    private boolean enabled;
//    @Convert(converter = StringToListConverter.class)
//    private List<String> cinemaCodes;
}
