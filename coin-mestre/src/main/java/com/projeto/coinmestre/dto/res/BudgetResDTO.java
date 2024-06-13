package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.domain.ExpenseStatus;
import com.projeto.coinmestre.model.Budget;
import com.projeto.coinmestre.model.Expense;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

public class BudgetResDTO {

    public BudgetResDTO(Budget budget) {
        this.id = budget.getId();
        this.goal = budget.getGoal();
        this.purchaseDate = budget.getPurchaseDate();
        this.totalValue = budget.getTotalValue();
        this.time = budget.getTime();
        this.totalReached = budget.getTotalReached();

    }

    private Long id;

    private String goal;

    private LocalDate purchaseDate;

    private Double totalValue;

    private String time;

    private Double totalReached;
}
