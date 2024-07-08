package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.model.Budget;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class BudgetResDTO {

    public BudgetResDTO(Budget budget) {
        this.id = budget.getId();
        this.deleted = budget.isDeleted();
        this.createdBy = budget.getCreatedBy();
        this.updatedBy = budget.getUpdatedBy();
        this.createdAt = budget.getCreatedAt();
        this.updatedAt = budget.getUpdatedAt();
        this.goal = budget.getGoal();
        this.totalReached = budget.getTotalReached();
        this.totalGoal = budget.getTotalGoal();
    }

    private Long id;

    private boolean deleted;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String goal;

    private Double totalReached;

    private Double totalGoal;
}
