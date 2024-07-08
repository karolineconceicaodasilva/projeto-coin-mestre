package com.projeto.coinmestre.repository;


import com.projeto.coinmestre.base.BaseRepository;
import com.projeto.coinmestre.model.Revenue;
import com.projeto.coinmestre.model.User;

import java.util.List;

public interface RevenueRepository extends BaseRepository<Revenue, Long> {
    List<Revenue> findAllByUser(User user);

}


