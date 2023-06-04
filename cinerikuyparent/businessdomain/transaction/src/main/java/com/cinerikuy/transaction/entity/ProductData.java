package com.cinerikuy.transaction.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productCode;
    private String productName;
    private double productPrice;
    private int productAmount;
    @ManyToOne
    private Transaction transaction;
}
