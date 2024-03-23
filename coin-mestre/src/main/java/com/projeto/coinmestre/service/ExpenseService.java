package com.projeto.coinmestre.service;

import com.projeto.coinmestre.dto.req.ExpenseReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseResDTO;
import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private ExpenseRepository repository;

    public List<ExpenseResDTO> findAllExpenses() {
        List<Expense> expenseList = this.repository.findAll();
        List<ExpenseResDTO> resDTOList = new ArrayList<>();

        for (Expense expense : expenseList) {
            ExpenseResDTO expenseResDTO = new ExpenseResDTO(expense);
            resDTOList.add(expenseResDTO);
        }

        return resDTOList;
    }

    public ExpenseResDTO findById(Long id) {
        Optional<Expense> optionalExpense = this.repository.findById(id);
        if (optionalExpense.isPresent()) {
            return new ExpenseResDTO(optionalExpense.get());
        } else {
            throw new RuntimeException("Despesa não encontrada na base de dados");
        }
    }

    public ExpenseResDTO insert(ExpenseReqDTO dto) {
        Expense expense = ExpenseReqDTO.dtoToModel(dto);
        return new ExpenseResDTO(this.repository.save(expense));
    }
}

