package com.projeto.coinmestre.repository;


import com.projeto.coinmestre.base.BaseRepository;
import com.projeto.coinmestre.model.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends BaseRepository<Expense, Long> {

    List<Expense> findAllByPurchaseDateBetween(LocalDate initPurchaseDate, LocalDate endPurchaseDate);
}




