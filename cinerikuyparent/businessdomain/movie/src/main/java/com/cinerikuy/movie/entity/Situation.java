package com.cinerikuy.movie.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cr_situation")
public class Situation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String situation;

    @Override
    public String toString() {
        return "Situation{" +
                "id=" + id +
                ", situation='" + situation + '\'' +
                '}';
    }
}
