package com.cinerikuy.transaction.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String transactionCode;
    private boolean paid;
    @Embedded
    private CustomerData customerData;
    @Embedded
    private CinemaData cinemaData;
    @Embedded
    private MovieData movieData;
    @OneToMany(mappedBy = "transaction")
    private Collection<ProductData> productDataList = new ArrayList<>();
}