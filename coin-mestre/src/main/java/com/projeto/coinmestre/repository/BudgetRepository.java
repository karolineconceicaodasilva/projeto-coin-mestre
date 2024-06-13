package com.projeto.coinmestre.repository;

import com.projeto.coinmestre.model.Budget;
import com.projeto.coinmestre.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findAllByPurchaseDateBetween(LocalDate initPurchaseDatePurchaseDate , LocalDate endPurchaseDate);
}
