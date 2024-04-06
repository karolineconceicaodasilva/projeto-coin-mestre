package com.projeto.coinmestre.model;

import com.projeto.coinmestre.domain.ExpenseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double value;

    private String category;

    private LocalDate purchaseDate;

    private LocalDate dueDate;

    private ExpenseStatus status;
}
