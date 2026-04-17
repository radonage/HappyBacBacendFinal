package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.model.Country;
import com.happyBackLast.happyBacklast.repository.CountryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin("*")
public class CountryController {

    private final CountryRepository repo;

    public CountryController(CountryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Country> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Country create(@RequestBody Country country) {
        return repo.save(country);
    }
}