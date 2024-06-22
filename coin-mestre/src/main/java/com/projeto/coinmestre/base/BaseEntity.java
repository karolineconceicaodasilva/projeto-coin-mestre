package com.projeto.coinmestre.base;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    protected boolean deleted;
    protected String createdBy;
    protected String updatedBy;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    @PrePersist
    private void beforeInsert() {
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
//        this.createdBy = AuthUtil.getUserName();
        this.updatedAt = this.createdAt;
        this.updatedBy = this.createdBy;
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
//        this.updatedBy = AuthUtil.getUserName();
    }

}
