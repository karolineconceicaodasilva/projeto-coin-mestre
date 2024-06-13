package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.domain.RevenueStatus;
import com.projeto.coinmestre.model.Revenue;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter

public class RevenueResDTO {

    public RevenueResDTO(Revenue revenue){
        this.id = revenue.getId();
        this.description = revenue.getDescription();
        this.value = revenue.getValue();
        this.purchaseDate = revenue.getPurchaseDate();
        this.dueDate = revenue.getDueDate();
        this.status = revenue.getStatus();
    }

    private Long id;

    private String description;

    private Double value;

    private LocalDate purchaseDate;

    private LocalDate dueDate;

    private RevenueStatus status;
}
