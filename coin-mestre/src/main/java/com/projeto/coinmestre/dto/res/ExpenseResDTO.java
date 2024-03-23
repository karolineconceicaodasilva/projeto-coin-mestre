package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.domain.ExpenseStatus;
import com.projeto.coinmestre.model.Expense;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class ExpenseResDTO {

    public ExpenseResDTO(Expense expense){
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.category = expense.getCategory();
        this.purchaseDate = expense.getPurchaseDate();
        this.dueDate = expense.getDueDate();
        this.status = expense.getStatus();
    }

    private Long id;

    private String description;

    private Double value;

    private String category;

    private LocalDate purchaseDate;

    private LocalDate dueDate;

    private ExpenseStatus status;
}
