package com.projeto.coinmestre.repository;


import com.projeto.coinmestre.base.BaseRepository;
import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.model.User;
import java.util.List;

public interface ExpenseRepository extends BaseRepository<Expense, Long> {

    List<Expense> findAllByUser(User user);
}




