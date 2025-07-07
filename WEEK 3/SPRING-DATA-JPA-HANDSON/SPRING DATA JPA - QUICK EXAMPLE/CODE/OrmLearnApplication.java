package com.cognizant.ormlearn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.CountryService;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
        System.out.println("Inside main");
    }

    @Override
    public void run(String... args) throws Exception {
     
        Country country = new Country();
        country.setCode("IN");
        country.setName("India");
        countryRepository.save(country);
        System.out.println("Saved country: " + country);

 
        Country retrieved = countryRepository.findById("IN").orElse(null);
        System.out.println("Retrieved country: " + retrieved);


        countryService.displayAllCountries();
    }
}
