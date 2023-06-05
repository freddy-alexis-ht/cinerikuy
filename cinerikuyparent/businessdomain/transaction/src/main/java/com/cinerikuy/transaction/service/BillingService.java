package com.cinerikuy.transaction.service;

import com.cinerikuy.transaction.entity.Billing;
import com.cinerikuy.transaction.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    public Billing post(Billing billing) {
        return billingRepository.save(billing);
    }

    public List<Billing> getBillings(List<String> transactionCodes) {
        return transactionCodes.stream()
                .map(t -> billingRepository.findByTransactionCode(t))
                .collect(Collectors.toList());
    }
}
