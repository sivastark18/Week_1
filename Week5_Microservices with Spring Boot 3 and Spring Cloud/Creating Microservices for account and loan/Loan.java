package com.example.loanservice.model;

public class Loan {
    private String loanId;
    private String accountId;
    private double amount;

    public Loan(String loanId, String accountId, double amount) {
        this.loanId = loanId;
        this.accountId = accountId;
        this.amount = amount;
    }

    // Getters
    public String getLoanId() { return loanId; }
    public String getAccountId() { return accountId; }
    public double getAmount() { return amount; }
}
