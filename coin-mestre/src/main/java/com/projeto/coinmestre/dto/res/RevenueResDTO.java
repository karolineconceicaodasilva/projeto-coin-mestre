package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.domain.RevenueStatus;
import com.projeto.coinmestre.model.Revenue;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter

public class RevenueResDTO {

    public RevenueResDTO(Revenue revenue){
        this.id = revenue.getId();
        this.deleted = revenue.isDeleted();
        this.createdBy = revenue.getCreatedBy();
        this.updatedBy = revenue.getUpdatedBy();
        this.createdAt = revenue.getCreatedAt();
        this.updatedAt = revenue.getUpdatedAt();
        this.description = revenue.getDescription();
        this.value = revenue.getValue();
        this.purchaseDate = revenue.getPurchaseDate();
        this.dueDate = revenue.getDueDate();
        this.status = revenue.getStatus();
    }

    private Long id;

    private boolean deleted;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String description;

    private Double value;

    private LocalDate purchaseDate;

    private LocalDate dueDate;

    private RevenueStatus status;
}
