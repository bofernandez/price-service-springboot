package com.example.prices.repository;

import com.example.prices.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId " +
            "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<Price> findApplicablePrices(@Param("applicationDate") LocalDateTime applicationDate,
                                     @Param("productId") Integer productId,
                                     @Param("brandId") Integer brandId);
}
