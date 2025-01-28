package com.example.Banking_springboot.controller;

import com.example.Banking_springboot.entity.Transaction;
import com.example.Banking_springboot.service.TransactionService;
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

//    // get all transactions
//    @GetMapping
//    public List<Transaction> getAllTransactions() {
//        return transactionService.getAllTransactions();
//    }

    // het transactions for a specific account
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
