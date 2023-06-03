package com.cinerikuy.transaction.service;

import com.cinerikuy.transaction.entity.Transaction;
import com.cinerikuy.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction post(Transaction transaction) {
        return transactionRepository.save(transaction);
    }


}
