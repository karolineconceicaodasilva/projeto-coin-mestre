package com.projeto.coinmestre.model;

import com.projeto.coinmestre.domain.ExpenseStatus;
import com.projeto.coinmestre.domain.RevenueStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "revenues")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double value;

    private LocalDate purchaseDate;

    private LocalDate dueDate;

    private RevenueStatus status;
}
