package com.cinerikuy.transaction.service;

import com.cinerikuy.transaction.entity.Transaction;
import com.cinerikuy.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction post(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getLast(String username) {
        Transaction transaction = this.findNonPaidByUsername(username);
        return transaction;
    }

    public Transaction findByTransactionCode(String transactionCode) {
        Optional<Transaction> transaction = transactionRepository.findByTransactionCode(transactionCode);
        if(!transaction.isPresent()) return null;
        return transaction.get();
    }

    public List<Transaction> findPaidByUsername(String username) {
        List<Transaction> transactions = transactionRepository.findByCustomerDataCustomerUsername(username);
        if(transactions.isEmpty() || transactions==null) return null;
        return transactions.stream().filter(t -> t.isPaid() == true).collect(Collectors.toList());
    }

    public Transaction findNonPaidByUsername(String username) {
        List<Transaction> transactions = transactionRepository.findByCustomerDataCustomerUsername(username);
        if(transactions.isEmpty() || transactions==null) return null;
        Optional<Transaction> transaction = transactions.stream()
                .filter(t -> !t.isPaid())
                .findFirst();
        if(!transaction.isPresent()) return null;
        return transaction.get();
    }

}
