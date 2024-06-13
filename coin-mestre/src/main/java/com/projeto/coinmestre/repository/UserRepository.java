package com.projeto.coinmestre.repository;

import com.projeto.coinmestre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}