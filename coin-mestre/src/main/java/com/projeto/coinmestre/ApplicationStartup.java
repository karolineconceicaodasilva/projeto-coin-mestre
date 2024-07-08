package com.projeto.coinmestre;


import com.projeto.coinmestre.domain.Role;
import com.projeto.coinmestre.model.User;
import com.projeto.coinmestre.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@AllArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        this.initDatabase();
    }

    private void initDatabase() {
        if (!this.userRepository.existsById(1L)) {
            HashSet<Role> roles = new HashSet<>(List.of(Role.ADMIN));
            this.userRepository.save(User
                    .builder()
                    .username("karol@gmail.com")
                    .name("Administrador do Sistema")
                    .email("karol@gmail.com")
                    .password(this.passwordEncoder.encode("1234"))
                    .roles(roles)
                    .build());

        }
    }
}