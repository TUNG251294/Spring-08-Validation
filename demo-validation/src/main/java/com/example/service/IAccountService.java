package com.example.service;

import com.example.model.UserAccount;

import java.util.List;
import java.util.Optional;

public interface IAccountService{
    List<UserAccount> findAll();
    Optional<UserAccount> findById(Long id);
    void save(UserAccount userAccount);
    void delete(Long id);
}
