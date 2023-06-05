package com.cinerikuy.transaction.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    private String username;
    private String transactionCode;
    private String cinemaName;
    private String movieName;
    private String movieSchedule;
    private LocalDateTime date;
    private double totalCost;
    @OneToOne
    private Transaction transaction;

}