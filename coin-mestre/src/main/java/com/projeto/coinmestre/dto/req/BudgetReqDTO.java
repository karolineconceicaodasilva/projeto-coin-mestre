package com.projeto.coinmestre.dto.req;

import com.projeto.coinmestre.model.Budget;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
public class BudgetReqDTO {

    @NotNull(message = "Obejetivo não deve ser nulo")
    private String goal;

    @DecimalMin(message = "Minímo R$1,00", value = "1")
    @NotNull
    private Double totalValue;

    @NotNull(message ="Data de lançamento não pode ser nula")
    @PastOrPresent
    private LocalDate purchaseDate;

    private String time;

    private Double totalReached;

    public static Budget dtoToModel(BudgetReqDTO dto){
        return Budget
                .builder()
                .goal(dto.getGoal())
                .purchaseDate(dto.getPurchaseDate())
                .totalValue(dto.getTotalValue())
                .time(dto.getTime())
                .totalReached(dto.getTotalReached())
                .build();
    }
}
