package com.projeto.coinmestre.dto.req;

import com.projeto.coinmestre.domain.ExpenseStatus;
import com.projeto.coinmestre.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
//ExpenseReqDTO(foi feita essa classe para ser passada as informações ao cliente)
@AllArgsConstructor
@Getter
@Setter
public class ExpenseReqDTO {

    @NotNull(message = "Descrição não deve ser nulo")
    private String description;

    @DecimalMin(message = "Minímo R$1,00", value = "1")
    @NotNull
    private Double value;

    @NotNull(message ="Categoria não pode ser nula")
    private String category;

    private LocalDate purchaseDate;

    private LocalDate dueDate;

    @NotNull(message = "Status válidos OPEN, CLOSE e OVERDUE")
    private ExpenseStatus status;


    public static Expense dtoToModel(ExpenseReqDTO dto){
        return Expense
                .builder()
                .category(dto.getCategory())
                .dueDate(dto.getDueDate())
                .purchaseDate(dto.getPurchaseDate())
                .status(dto.getStatus())
                .description(dto.getDescription())
                .value(dto.getValue())
                .build();
    }
}
