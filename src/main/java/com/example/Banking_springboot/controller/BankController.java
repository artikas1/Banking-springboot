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

