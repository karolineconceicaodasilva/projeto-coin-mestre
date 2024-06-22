package com.projeto.coinmestre.repository;

import com.projeto.coinmestre.base.BaseRepository;
import com.projeto.coinmestre.model.Budget;

import java.time.LocalDate;
import java.util.List;

public interface BudgetRepository extends BaseRepository<Budget, Long> {
    List<Budget> findAllByPurchaseDateBetween(LocalDate initPurchaseDatePurchaseDate, LocalDate endPurchaseDate);
}
