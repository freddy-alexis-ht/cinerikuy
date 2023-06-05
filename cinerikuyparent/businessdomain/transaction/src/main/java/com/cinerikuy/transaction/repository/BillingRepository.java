package com.cinerikuy.transaction.repository;

import com.cinerikuy.transaction.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    Billing findByTransactionCode(String transactionCode);
}
