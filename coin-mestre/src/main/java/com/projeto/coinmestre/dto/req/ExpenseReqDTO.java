package com.projeto.coinmestre.dto.req;

import com.projeto.coinmestre.domain.ExpenseStatus;
import com.projeto.coinmestre.model.Expense;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    @NotNull(message ="Data de lançamento não pode ser nula")
    @PastOrPresent
    private LocalDate purchaseDate;

    @NotNull(message ="Data de vencimento não pode ser nula")
    @FutureOrPresent
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
