package com.cinerikuy.movie.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cr_vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vote;

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", vote='" + vote + '\'' +
                '}';
    }
}
