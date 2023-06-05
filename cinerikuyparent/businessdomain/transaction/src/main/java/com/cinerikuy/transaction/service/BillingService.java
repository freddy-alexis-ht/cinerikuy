package com.cinerikuy.transaction.service;

import com.cinerikuy.transaction.entity.Billing;
import com.cinerikuy.transaction.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    public Billing post(Billing billing) {
        return billingRepository.save(billing);
    }
}
