package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserResDTO {

    public UserResDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    private Long id;

    private String name;

    private String email;

}
