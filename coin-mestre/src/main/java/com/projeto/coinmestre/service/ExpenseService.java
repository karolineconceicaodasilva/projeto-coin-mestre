package com.projeto.coinmestre.service;

import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {

    private ExpenseRepository repository;

    public List<Expense> findAllExpenses() {
        return this.repository.findAll();
    }
}
