package com.example.Banking_springboot.controller;

import com.example.Banking_springboot.service.BankAccountService;
import com.example.Banking_springboot.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bank")
public class BankController {

    private final TransactionService transactionService;
    private final BankAccountService bankAccountService;

    public BankController(TransactionService transactionService, BankAccountService bankAccountService) {
        this.transactionService = transactionService;
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/transfer")
    public String transfer(
            @RequestParam Long sourceAccountId,
            @RequestParam Long targetAccountId,
            @RequestParam double amount) {
        transactionService.transfer(sourceAccountId, targetAccountId, amount);
        return "Transferred " + amount + " from account " + sourceAccountId + " to account " + targetAccountId;
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountId, @RequestParam double amount) {
        transactionService.withdraw(accountId, amount);
        return "Withdrew " +  amount + " from account " + accountId;
    }
    @PostMapping("/deposit")
    public String deposit(@RequestParam Long accountId, @RequestParam double amount) {
        transactionService.deposit(accountId, amount);
        return "Deposited " + amount + " into account " + accountId;
    }

    @GetMapping("/balance")
    public String getBalance(@RequestParam Long accountId) {
        double balance = bankAccountService.getAccountById(accountId).getBalance();
        return "Current balance of account " + accountId + ": " + balance;
    }

    @GetMapping("/target-balance")
    public String getTargetAccountBalance(@RequestParam Long targetAccountId) {
        double balance = bankAccountService.getAccountById(targetAccountId).getBalance();
        return "Target account " + targetAccountId + " balance: " + balance;
    }

    @PostMapping("/create-account")
    public String createBankAccount(@RequestParam Long userId, @RequestParam double initialBalance) {
        bankAccountService.createBankAccount(userId, initialBalance);
        return "Bank account created successfully for user ID: " + userId;
    }
}

