package com.cinerikuy.product.service;

import com.cinerikuy.product.entity.Product;
import com.cinerikuy.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    public ProductRepository productRepository;

    public List<Product> productFindAll() {
        List<Product> list = productRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public Product productFindById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()) return null;
        return product.get();
    }

    public Product productSaveUpdate(Product product) {
        return productRepository.save(product);
    }

}
