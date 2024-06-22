package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.domain.ExpenseStatus;
import com.projeto.coinmestre.model.Expense;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class ExpenseResDTO {

    public ExpenseResDTO(Expense expense){
        this.id = expense.getId();
        this.deleted = expense.isDeleted();
        this.createdBy = expense.getCreatedBy();
        this.updatedBy = expense.getUpdatedBy();
        this.createdAt = expense.getCreatedAt();
        this.updatedAt = expense.getUpdatedAt();
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.category = expense.getCategory();
        this.purchaseDate = expense.getPurchaseDate();
        this.dueDate = expense.getDueDate();
        this.status = expense.getStatus();
    }

    private Long id;

    private boolean deleted;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String description;

    private Double value;

    private String category;

    private LocalDate purchaseDate;

    private LocalDate dueDate;

    private ExpenseStatus status;
}
