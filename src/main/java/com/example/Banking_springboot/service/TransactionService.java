package com.example.Banking_springboot.service;

import com.example.Banking_springboot.entity.BankAccount;
import com.example.Banking_springboot.entity.Transaction;
import com.example.Banking_springboot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final BankAccountService bankAccountService;
    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(BankAccountService bankAccountService, TransactionRepository transactionRepository) {
        this.bankAccountService = bankAccountService;
        this.transactionRepository = transactionRepository;
    }

    public void transfer(Long sourceAccountId, Long targetAccountId, double amount, Long loggedInUserId) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        BankAccount sourceAccount = bankAccountService.getAccountById(sourceAccountId);

        if (!sourceAccount.getUser().getId().equals(loggedInUserId)) {
            throw new IllegalArgumentException("You can only transfer from accounts you own!");
        }

        BankAccount targetAccount = bankAccountService.getAccountById(targetAccountId);

        if (sourceAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        bankAccountService.saveAccount(sourceAccount);
        bankAccountService.saveAccount(targetAccount);

        Transaction transaction = new Transaction(amount, "TRANSFER", sourceAccount, targetAccount);
        transactionRepository.save(transaction);
    }

    public void deposit(Long accountId, double amount, Long loggedInUserId) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        BankAccount account = bankAccountService.getAccountById(accountId);

        if (!account.getUser().getId().equals(loggedInUserId)) {
            throw new IllegalArgumentException("You can only deposit from accounts you own!");
        }

        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction(amount, "DEPOSIT", account);
        transactionRepository.save(transaction);

        bankAccountService.saveAccount(account);
    }

    public void withdraw(Long accountId, double amount, Long loggedInUserId) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        BankAccount account = bankAccountService.getAccountById(accountId);

        if (!account.getUser().getId().equals(loggedInUserId)) {
            throw new IllegalArgumentException("You can only withdraw from accounts you own!");
        }

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);

        // log transaction
        Transaction transaction = new Transaction(amount, "WITHDRAW", account);
        transactionRepository.save(transaction);

        bankAccountService.saveAccount(account);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    // filter transactions by type
//    public List<Transaction> getTransactionsByType(String type) {
//        return transactionRepository.findByType(type);
//    }

}
