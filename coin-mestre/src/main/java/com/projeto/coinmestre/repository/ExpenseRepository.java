package com.projeto.coinmestre.repository;


import com.projeto.coinmestre.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByPurchaseDateBetween(LocalDate initPurchaseDate, LocalDate endPurchaseDate);
}




