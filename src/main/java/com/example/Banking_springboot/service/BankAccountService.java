package com.example.Banking_springboot.service;

import com.example.Banking_springboot.entity.BankAccount;
import com.example.Banking_springboot.entity.User;
import com.example.Banking_springboot.repository.BankAccountRepository;
import com.example.Banking_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public double getBalance(Long accountId) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return account.getBalance();
    }

    public BankAccount getAccountById(Long accountId) {
        return bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    public BankAccount saveAccount(BankAccount account) {
        return bankAccountRepository.save(account);
    }

    public BankAccount createBankAccount(Long userId, double initialBalance) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        BankAccount newAccount = new BankAccount();
        newAccount.setBalance(initialBalance);
        newAccount.setUser(user);

        return bankAccountRepository.save(newAccount);
    }
}
