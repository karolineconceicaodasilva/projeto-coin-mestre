package com.projeto.coinmestre.model;

import com.projeto.coinmestre.base.BaseEntity;
import com.projeto.coinmestre.domain.RevenueStatus;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "revenues")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
public class Revenue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double value;

    private LocalDate purchaseDate;

    private LocalDate dueDate;

    private RevenueStatus status;

    @ManyToOne
    private User user;
}
