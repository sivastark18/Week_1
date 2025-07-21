package com.example.accountservice.controller;

import com.example.accountservice.model.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable String id) {
        return new Account(id, "Gogul Krishna", 50000.0);
    }
}
