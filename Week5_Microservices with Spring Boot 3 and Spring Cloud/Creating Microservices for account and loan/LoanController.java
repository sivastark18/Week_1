package com.example.loanservice.controller;

import com.example.loanservice.model.Loan;
import com.example.loanservice.service.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private AccountClient accountClient;

    @GetMapping("/{id}")
    public String getLoanDetails(@PathVariable String id) {
        Loan loan = new Loan(id, "ACC1001", 25000.0);
        String accountInfo = accountClient.getAccountDetails(loan.getAccountId());
        return "Loan ID: " + loan.getLoanId() + ", Amount: " + loan.getAmount() + "\nAccount Info: " + accountInfo;
    }
}
