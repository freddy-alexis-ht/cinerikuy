package com.cinerikuy.transaction.service;

import com.cinerikuy.transaction.entity.Transaction;
import com.cinerikuy.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction post(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getLast(String username) {
        List<Transaction> list = transactionRepository.findAll();
        if(list == null || list.isEmpty())
            return null;
        Optional<Transaction> transaction = list.stream()
                .filter(t -> !t.isPaid())
                .findFirst();
        if(!transaction.isPresent()) return null;
        return transaction.get();
    }

}
