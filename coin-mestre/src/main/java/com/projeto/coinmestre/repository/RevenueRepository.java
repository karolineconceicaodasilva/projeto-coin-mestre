package com.projeto.coinmestre.repository;


import com.projeto.coinmestre.base.BaseRepository;
import com.projeto.coinmestre.model.Revenue;

import java.time.LocalDate;
import java.util.List;

public interface RevenueRepository extends BaseRepository<Revenue, Long> {
    List<Revenue> findAllByPurchaseDateBetween(LocalDate initPurchaseDate, LocalDate endPurchaseDate);

}


