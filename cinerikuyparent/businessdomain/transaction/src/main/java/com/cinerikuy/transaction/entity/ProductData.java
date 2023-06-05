package com.cinerikuy.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Transaction transaction;

    @Override
    public String toString() {
        return "ProductData{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productAmount=" + productAmount +
                '}';
    }
}
