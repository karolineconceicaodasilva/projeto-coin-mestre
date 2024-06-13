package com.projeto.coinmestre.dto.req;

import com.projeto.coinmestre.domain.RevenueStatus;
import com.projeto.coinmestre.model.Revenue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class RevenueReqDTO {

    @NotNull(message = "Receita não deve ser nulo")
    private String description;

    @DecimalMin(message = "Minímo R$1,00", value = "1")
    @NotNull
    private Double value;


    @NotNull(message ="Data de lançamento não pode ser nula")
    @PastOrPresent
    private LocalDate purchaseDate;

    @NotNull(message ="Data de vencimento não pode ser nula")
    @FutureOrPresent
    private LocalDate dueDate;

    @NotNull(message = "Status válidos OPEN, CLOSE e OVERDUE")
    private RevenueStatus status;

    public static Revenue dtoToModel(RevenueReqDTO dto){
        return Revenue
                .builder()
                .dueDate(dto.getDueDate())
                .purchaseDate(dto.getPurchaseDate())
                .status(dto.getStatus())
                .description(dto.getDescription())
                .value(dto.getValue())
                .build();
    }
}
