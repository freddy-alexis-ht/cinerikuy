package com.cinerikuy.cinema.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cr_cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cinemaCode;
    private String name;
    private String address;
    private String district;
    private double ticketPrice;
    @ManyToOne
    private City city;
    private boolean enabled;

}
