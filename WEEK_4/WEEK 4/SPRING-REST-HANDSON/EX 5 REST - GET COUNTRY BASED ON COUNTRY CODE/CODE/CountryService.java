package com.cognizant.springlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.model.Country;

@Service
public class CountryService {

    @SuppressWarnings("unchecked")
    public Country getCountry(String code) {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList");

        Optional<Country> country = countryList.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();

        if (country.isPresent()) {
            return country.get();
        } else {
            throw new RuntimeException("Country not found: " + code);
        }
    }
}
