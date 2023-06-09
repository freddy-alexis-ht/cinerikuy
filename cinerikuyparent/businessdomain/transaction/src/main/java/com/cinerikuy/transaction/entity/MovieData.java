package com.cinerikuy.transaction.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class MovieData {
    private String movieCode;
    private String movieName;
    private String movieSchedule;
    private String movieLanguage;
    private int movieNumberOfTickets;
}
