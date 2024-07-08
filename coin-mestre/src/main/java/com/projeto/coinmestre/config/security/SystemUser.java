package com.projeto.coinmestre.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class SystemUser extends User {

    private Long id;
    private String name;
    private String email;

    public SystemUser(Long id, String username, String password, boolean enabled,
                      Collection<? extends GrantedAuthority> authorities, String name, String email) {
        super(username, password, enabled, true, true, true, authorities);
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
