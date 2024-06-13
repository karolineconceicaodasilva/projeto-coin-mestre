package com.projeto.coinmestre.repository;


import com.projeto.coinmestre.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    List<Revenue> findAllByPurchaseDateBetween(LocalDate initPurchaseDate, LocalDate endPurchaseDate);

}


