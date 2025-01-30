package com.example.Banking_springboot.service;

import com.example.Banking_springboot.entity.BankAccount;
import com.example.Banking_springboot.entity.User;
import com.example.Banking_springboot.repository.BankAccountRepository;
import com.example.Banking_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

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
        newAccount.setAccountNumber(generateAccountNumber(user.getFirstName(), user.getLastName()));
        newAccount.setUser(user);

        return bankAccountRepository.save(newAccount);
    }

    private String generateAccountNumber(String firstName, String lastName) {
        String firstThreeNameLetters = firstName.substring(0, 3).toUpperCase();
        String firstThreeSurnameLetters = lastName.substring(0, 3).toUpperCase();;
        String randomAlphanumeric = generateRandomAlphanumeric(12);

        return firstThreeNameLetters + firstThreeSurnameLetters + "_" + randomAlphanumeric;
    }

    private String generateRandomAlphanumeric(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }
}
