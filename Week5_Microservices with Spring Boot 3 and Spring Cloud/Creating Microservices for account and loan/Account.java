package com.example.accountservice.model;

public class Account {
    private String accountId;
    private String name;
    private double balance;

    // Constructor, Getters, Setters
    public Account(String accountId, String name, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountId() { return accountId; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
}
