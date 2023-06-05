package com.cinerikuy.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Collection<ProductData> productDataList = new ArrayList<>();

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionCode='" + transactionCode + '\'' +
                ", paid=" + paid +
                ", customerData=" + customerData +
                ", cinemaData=" + cinemaData +
                ", movieData=" + movieData +
                '}';
    }
}