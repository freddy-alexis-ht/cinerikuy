package com.cinerikuy.movie.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class VotingPK implements Serializable {
    private static final long serialVersionUID = 2294611566081419084L;
    private long movieId;
    private String username;
}