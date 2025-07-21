package com.example.loanservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccountDetails(String accountId) {
        String url = "http://localhost:8081/accounts/" + accountId;
        return restTemplate.getForObject(url, String.class);
    }
}
