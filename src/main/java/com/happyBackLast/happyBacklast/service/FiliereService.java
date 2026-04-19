package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.FiliereRequest;
import com.happyBackLast.happyBacklast.model.Filiere;

import java.util.List;

public interface FiliereService {

    List<Filiere> getByCountry(Long countryId);

    Filiere create(FiliereRequest request);

    Filiere update(Long id, FiliereRequest request);

    void delete(Long id);
}