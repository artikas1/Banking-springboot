package com.example.Banking_springboot.controller;

import com.example.Banking_springboot.entity.Transaction;
import com.example.Banking_springboot.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/transfer")
    public String transfer(
            @RequestParam Long sourceAccountId,
            @RequestParam Long targetAccountId,
            @RequestParam double amount,
            HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId"); // retrieve user ID from session
        if (loggedInUserId == null) {
            throw new IllegalStateException("User is not logged in");
        }
        transactionService.transfer(sourceAccountId, targetAccountId, amount, loggedInUserId);
        return "Transferred " + amount + " from account " + sourceAccountId + " to account " + targetAccountId;
    }

    @PostMapping("/withdraw")
    public String withdraw(
            @RequestParam Long accountId,
            @RequestParam double amount,
            HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId"); // retrieve user ID from session
        if (loggedInUserId == null) {
            throw new IllegalStateException("User is not logged in");
        }
        transactionService.withdraw(accountId, amount, loggedInUserId);
        return "Withdrew " +  amount + " from account " + accountId;
    }
    @PostMapping("/deposit")
    public String deposit(
            @RequestParam Long accountId,
            @RequestParam double amount,
            HttpSession session) { // userId injected directly
        Long loggedInUserId = (Long) session.getAttribute("userId"); // retrieve user ID from session
        if (loggedInUserId == null) {
            throw new IllegalStateException("User is not logged in");
        }
        transactionService.deposit(accountId, amount, loggedInUserId);
        return "Deposited " + amount + " into account " + accountId;
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccount(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }

    // filter transactions by type
//    @GetMapping("/filter")
//    public List<Transaction> getTransactionsByType(@RequestParam String type) {
//        return transactionService.getTransactionsByType(type);
//    }
}
