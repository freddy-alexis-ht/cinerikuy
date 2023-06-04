package com.cinerikuy.transaction.repository;

import com.cinerikuy.transaction.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, Long> {
}
