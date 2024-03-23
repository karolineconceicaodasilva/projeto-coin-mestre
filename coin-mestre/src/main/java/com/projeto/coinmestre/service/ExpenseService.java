package com.projeto.coinmestre.service;

import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private ExpenseRepository repository;

    public List<Expense> findAllExpenses() {

        return this.repository.findAll();
    }

    public Expense findById(Long id) {

        Optional<Expense> optionalExpense = this.repository.findById(id);

        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            throw new RuntimeException("Despesa não encontrada na base de dados");
        }

    }
}
