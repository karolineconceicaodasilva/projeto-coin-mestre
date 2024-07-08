package com.projeto.coinmestre.dto.req;

import com.projeto.coinmestre.model.Budget;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class BudgetReqDTO {

    @NotNull(message = "Obejetivo não deve ser nulo")
    private String goal;

    private Double totalReached;

    @DecimalMin(message = "Minímo R$1,00", value = "1")
    @NotNull
    private Double totalGoal;

    public static Budget dtoToModel(BudgetReqDTO dto){
        return Budget
                .builder()
                .goal(dto.getGoal())
                .totalReached(dto.getTotalReached())
                .totalGoal(dto.getTotalGoal())
                .build();
    }
}
