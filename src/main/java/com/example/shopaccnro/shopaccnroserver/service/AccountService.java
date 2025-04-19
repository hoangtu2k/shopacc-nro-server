package com.example.shopaccnro.shopaccnroserver.service;

import com.example.shopaccnro.shopaccnroserver.entity.Account;
import com.example.shopaccnro.shopaccnroserver.repository.AccountRepository;
import com.example.shopaccnro.shopaccnroserver.request.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }

    public Account getAccountById(Long id) {
        return accountRepository.getById(id);
    }

    public Account createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setUsername(accountRequest.getUsername());
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        account.setStatus(1);
        return accountRepository.save(account);
    }

}
