package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.dto.FiliereRequest;
import com.happyBackLast.happyBacklast.model.Country;
import com.happyBackLast.happyBacklast.model.Filiere;
import com.happyBackLast.happyBacklast.repository.CountryRepository;
import com.happyBackLast.happyBacklast.repository.FiliereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {

    private final FiliereRepository filiereRepository;
    private final CountryRepository countryRepository;

    public FiliereService(FiliereRepository filiereRepository,
                          CountryRepository countryRepository) {
        this.filiereRepository = filiereRepository;
        this.countryRepository = countryRepository;
    }

    public List<Filiere> getByCountry(Long countryId) {

        return filiereRepository.findFilieresByCountryId(countryId);
    }


    public Filiere create(FiliereRequest request) {

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country introuvable"));

        Filiere f = new Filiere();
        f.setName(request.getName());
        f.setCountry(country);

        return filiereRepository.save(f);
    }

    public Filiere update(Long id, FiliereRequest request) {

        Filiere f = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country introuvable"));

        f.setName(request.getName());
        f.setCountry(country);

        return filiereRepository.save(f);
    }

    public void delete(Long id) {
        filiereRepository.deleteById(id);
    }
}