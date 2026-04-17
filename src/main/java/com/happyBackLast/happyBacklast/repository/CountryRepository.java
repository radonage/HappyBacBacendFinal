package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Country;
import com.happyBackLast.happyBacklast.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
}