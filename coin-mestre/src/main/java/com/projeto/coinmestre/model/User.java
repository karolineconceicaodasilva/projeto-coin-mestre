package com.projeto.coinmestre.model;

import com.projeto.coinmestre.base.BaseEntity;
import com.projeto.coinmestre.domain.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    private String name;

    @NotBlank
    private String email;

    private String password;

    @OneToMany
    private List<Revenue> revenues;

    @OneToMany
    private List<Expense> expenses;

    @OneToMany
    private List<Budget> budgets;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private Set<Role> roles;
}
