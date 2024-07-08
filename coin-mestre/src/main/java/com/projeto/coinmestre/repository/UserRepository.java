package com.projeto.coinmestre.repository;

import com.projeto.coinmestre.base.BaseRepository;
import com.projeto.coinmestre.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByUsername(String username);
}