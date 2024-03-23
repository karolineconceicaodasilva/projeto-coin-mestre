package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService service;

    @GetMapping
    public List<Expense> findAll(){

        return this.service.findAllExpenses();
    }
}
