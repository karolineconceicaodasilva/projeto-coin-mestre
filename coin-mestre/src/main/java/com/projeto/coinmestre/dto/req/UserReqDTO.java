package com.projeto.coinmestre.dto.req;

import com.projeto.coinmestre.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//ExpenseReqDTO(foi feita essa classe para ser passada as informações ao cliente)
@AllArgsConstructor
@Getter
@Setter
public class UserReqDTO {

    private String name;

    private String email;

    private String password;

    private String confirmationPassword;

    public static User dtoToModel(UserReqDTO dto){
        return User
                .builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
