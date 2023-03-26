package com.example.service;

import com.example.model.UserAccount;
import com.example.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository accountRepository;

    @Override
    public List<UserAccount> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<UserAccount> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void save(UserAccount userAccount) {
        accountRepository.save(userAccount);

    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
