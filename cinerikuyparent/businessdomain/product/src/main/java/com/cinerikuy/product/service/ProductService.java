package com.cinerikuy.product.service;

import com.cinerikuy.product.entity.Product;
import com.cinerikuy.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public List<Product> findAll() {
        List<Product> list = productRepository.findAll();
        if(list == null || list.isEmpty())
            return null;
        return list.stream()
                .filter(p -> p.isEnabled())
                .collect(Collectors.toList());
    }

    public Product findByProductCode(String productCode) {
        Optional<Product> product = productRepository.findByProductCode(productCode);
        if(!product.isPresent()) return null;
        if(!product.get().isEnabled()) return null;
        return product.get();
    }

}
