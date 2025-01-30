package com.example.Banking_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private BankAccount account;

    @ManyToOne
    @JoinColumn(name = "target_account_id") // optional for transfers
    private BankAccount targetAccount;

    @Column(nullable = false)
    private String transactionType; // deposit, withdraw, transfer

    @Column(nullable = false)
    private Double amount;

    private LocalDateTime timestamp = LocalDateTime.now();

    public Transaction(Double amount, String transactionType, BankAccount account) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.account = account;
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(Double amount, String transactionType, BankAccount account, BankAccount targetAccount) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.account = account;
        this.targetAccount = targetAccount;
        this.timestamp = LocalDateTime.now();
    }

    public Transaction() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public BankAccount getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(BankAccount targetAccount) {
        this.targetAccount = targetAccount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
