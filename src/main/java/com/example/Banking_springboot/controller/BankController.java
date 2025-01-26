package com.example.Banking_springboot.controller;

import com.example.Banking_springboot.service.BankService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam double amount) {
        bankService.deposit(amount);
        return "Deposited " + amount;
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam double amount) {
        bankService.withdraw(amount);
        return "Withdrew " + amount;
    }

    @GetMapping("/balance")
    public String getBalance() {
        return "Current balance: " + bankService.getBalance();
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam double amount) {
        bankService.transfer(amount);
        return "Transferred " + amount;
    }

    @GetMapping("/target-balance")
    public String getTargetAccountBalance(){
        return "Target account balance: " + bankService.getTargetAccountBalance();
    }
}

