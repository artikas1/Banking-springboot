package com.example.Banking_springboot.service;

public class BankAccount {
    private double balance;

    public BankAccount() {
        balance = 500;
    }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            balance+=amount;
        }
        else {
            throw new IllegalArgumentException("Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds!");
        } else {
            balance -= amount;
        }
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive!");
        } else if(balance < amount) {
            throw new IllegalArgumentException("Insufficient funds!");
        } else {
            this.balance -= amount;
            targetAccount.balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
