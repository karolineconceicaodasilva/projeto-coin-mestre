package com.projeto.coinmestre.service;

import com.projeto.coinmestre.dto.req.BudgetReqDTO;
import com.projeto.coinmestre.dto.res.BudgetResDTO;
import com.projeto.coinmestre.dto.res.BudgetValueResDTO;
import com.projeto.coinmestre.model.Budget;
import com.projeto.coinmestre.repository.BudgetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BudgetService {

    private BudgetRepository repository;

    public List<BudgetResDTO> findAllBudgets() {

        List<Budget> budgetList = this.repository.findAll();


        List<BudgetResDTO> resDTOList = new ArrayList<>();

        for (Budget budget : budgetList) {

            BudgetResDTO budgetResDTO = new BudgetResDTO(budget);

            resDTOList.add(budgetResDTO);
        }
        return resDTOList;
    }

    public BudgetResDTO findById(Long id) {
        Optional<Budget> optionalBudget = this.repository.findById(id);
        if (optionalBudget.isPresent()) {
            return new BudgetResDTO(optionalBudget.get());
        } else {
            throw new RuntimeException("Orçamento não encontrada na base de dados");
        }
    }

    public BudgetResDTO insert(BudgetReqDTO dto) {

        Budget budget = BudgetReqDTO.dtoToModel(dto);

        this.repository.save(budget);

        BudgetResDTO resDTO = new BudgetResDTO(budget);

        return resDTO;
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);

    }

    public BudgetResDTO update(Long id, BudgetReqDTO dto) {

        Optional<Budget> optionalBudget = this.repository.findById(id);
        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            budget.setGoal(dto.getGoal());
            budget.setTotalValue(dto.getTotalValue());
            budget.setTotalReached(dto.getTotalReached());
            budget.setTime(dto.getTime());
            budget.setPurchaseDate(dto.getPurchaseDate());
            this.repository.save(budget);

            return new BudgetResDTO(budget);
        } else {

            throw new RuntimeException("Orçamento não encontrada na base de dados.");
        }
    }

    public BudgetValueResDTO valueOfBudgets() {
        double totalValue = this.repository.findAll().stream().mapToDouble(Budget::getTotalValue).sum();
        long quantit = this.repository.findAll().size();
        return new BudgetValueResDTO(totalValue, quantit);
    }

    public List<BudgetResDTO> findAllByPurchaseDate(LocalDate initPurchaseDate,
                                                     LocalDate endPurchaseDate) {
        List<Budget> budgetList = this.repository.findAllByPurchaseDateBetween(initPurchaseDate, endPurchaseDate);

        List<BudgetResDTO> resDTOList = new ArrayList<>();

        for (Budget budget : budgetList) {

            BudgetResDTO budgetResDTO = new BudgetResDTO(budget);

            resDTOList.add(budgetResDTO);
        }
        return resDTOList;
    }
}



