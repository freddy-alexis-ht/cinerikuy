package com.cinerikuy.transaction.service;

import com.cinerikuy.transaction.entity.ProductData;
import com.cinerikuy.transaction.repository.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDataService {

    @Autowired
    private ProductDataRepository repository;

    public void saveProductData(ProductData p) {
        repository.save(p);
    }

    public List<ProductData> findByTransactionId(long transactionId) {
        List<ProductData> lista = repository.findByTransactionId(transactionId);
        if(lista.isEmpty() || lista == null)
            return null;
        return lista;
    }
}
