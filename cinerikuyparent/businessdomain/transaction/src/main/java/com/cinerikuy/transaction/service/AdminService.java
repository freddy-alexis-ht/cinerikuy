package com.cinerikuy.transaction.service;

import com.cinerikuy.transaction.entity.Billing;
import com.cinerikuy.transaction.entity.ProductData;
import com.cinerikuy.transaction.entity.Transaction;
import com.cinerikuy.transaction.repository.BillingRepository;
import com.cinerikuy.transaction.repository.ProductDataRepository;
import com.cinerikuy.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    public TransactionRepository transactionRepository;
    @Autowired
    public ProductDataRepository productDataRepository;
    @Autowired
    public BillingRepository billingRepository;

    /** TRANSACTION */

    public List<Transaction> transactionFindAll() {
        List<Transaction> list = transactionRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public Transaction transactionFindById(long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(!transaction.isPresent()) return null;
        return transaction.get();
    }

    /** PRODUCT-DATA */

    public List<ProductData> productDataFindAll() {
        List<ProductData> list = productDataRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public ProductData productDataFindById(long id) {
        Optional<ProductData> productData = productDataRepository.findById(id);
        if(!productData.isPresent()) return null;
        return productData.get();
    }

    public List<ProductData> productDataFindByTransactionId(long transactionId) {
        List<ProductData> list = productDataRepository.findByTransactionId(transactionId);
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    /** BILLING */

    public List<Billing> billingFindAll() {
        List<Billing> list = billingRepository.findAll();
        if(list == null || list.isEmpty()) return null;
        return list;
    }

    public Billing billingFindById(long id) {
        Optional<Billing> billing = billingRepository.findById(id);
        if(!billing.isPresent()) return null;
        return billing.get();
    }

}
