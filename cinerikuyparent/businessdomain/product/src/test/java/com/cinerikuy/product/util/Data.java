package com.cinerikuy.product.util;

import com.cinerikuy.product.dto.ProductResponse;
import com.cinerikuy.product.entity.Product;

import java.util.Optional;

public class Data {
    public static Optional<ProductResponse> createProductResponse01() {
        return Optional.of(new ProductResponse("P01", "Popcorn Pequeño", "https://...", "7.0"));
    }
    public static Optional<ProductResponse> createProductResponse02() {
        return Optional.of(new ProductResponse("P02", "Popcorn Mediano", "https://...", "10.0"));
    }
    public static Optional<Product> createProduct01() {
        return Optional.of(new Product(1L, "P01", "Popcorn Pequeño", "https://...", 7.0, true));
    }
    public static Optional<Product> createProduct02() {
        return Optional.of(new Product(2L, "P02", "Popcorn Mediano", "https://...", 10.0, true));
    }

}
