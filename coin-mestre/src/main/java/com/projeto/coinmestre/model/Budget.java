package com.projeto.coinmestre.model;

import com.projeto.coinmestre.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "budgets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Budget extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goal;

    private LocalDate purchaseDate;

    private Double totalValue;

    private String time;

    private Double totalReached;
}
