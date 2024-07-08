package com.projeto.coinmestre.model;

import com.projeto.coinmestre.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "budgets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
public class Budget extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goal;

    private Double totalReached;

    private Double totalGoal;

    @ManyToOne
    private User user;
}
