package com.projeto.coinmestre.dto.req;

import com.projeto.coinmestre.domain.Role;
import com.projeto.coinmestre.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

//ExpenseReqDTO(foi feita essa classe para ser passada as informações ao cliente)
@AllArgsConstructor
@Getter
@Setter
public class UserReqDTO {

    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String confirmationPassword;

    @NotNull
    private Set<Role> roles;

    public static User dtoToModel(UserReqDTO dto) {
        return User
                .builder()
                .name(dto.getName())
                .email(dto.getEmail().trim().toLowerCase())
                .username(dto.getEmail().trim().toLowerCase())
                .roles(dto.getRoles())
                .build();
    }
}
