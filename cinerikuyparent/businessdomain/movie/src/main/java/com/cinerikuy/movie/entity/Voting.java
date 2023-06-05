package com.cinerikuy.movie.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
@Data
public class Voting {
    @EmbeddedId
    private VotingPK votingPK;

    @MapsId("movieId")
    @ManyToOne
    private Movie movie;
}

@Embeddable
class VotingPK implements Serializable {
    private static final long serialVersionUID = 2294611566081419084L;
    private int movieId;
    private String username;
}