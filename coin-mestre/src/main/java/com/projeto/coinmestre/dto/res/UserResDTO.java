package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class UserResDTO {

    public UserResDTO(User user) {
        this.id = user.getId();
        this.deleted = user.isDeleted();
        this.createdBy = user.getCreatedBy();
        this.updatedBy = user.getUpdatedBy();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    private Long id;

    private boolean deleted;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private String email;

}
