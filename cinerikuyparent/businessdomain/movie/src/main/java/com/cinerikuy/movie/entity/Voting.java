package com.cinerikuy.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cr_voting")
public class Voting {
    @EmbeddedId
    private VotingPK votingPK;

    @MapsId("movieId")
    @ManyToOne
    @JsonIgnore
    private Movie movie;

    @Override
    public String toString() {
        return "Voting{" +
                "votingPK=" + votingPK +
                '}';
    }
}
