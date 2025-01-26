package com.example.Banking_springboot.service;

import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final BankAccount bankAccount = new BankAccount();
    private final BankAccount targetAccount = new BankAccount();

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public void withdraw(double amount) {
        bankAccount.withdraw(amount);
    }

    public double getBalance() {
        return bankAccount.getBalance();
    }

    public void transfer(double amount) {
        bankAccount.transfer(targetAccount, amount);
    }

    public double getTargetAccountBalance() {
        return targetAccount.getBalance();
    }
}
