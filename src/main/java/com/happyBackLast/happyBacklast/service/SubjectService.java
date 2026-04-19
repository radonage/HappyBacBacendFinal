package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.dto.SubjectDTO;
import com.happyBackLast.happyBacklast.model.Country;
import com.happyBackLast.happyBacklast.model.Subject;
import com.happyBackLast.happyBacklast.repository.CountryRepository;
import com.happyBackLast.happyBacklast.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository repo;
    private final CountryRepository countryRepository;

    public SubjectService(SubjectRepository repo, CountryRepository countryRepository) {
        this.repo = repo;
        this.countryRepository = countryRepository;
    }

    public List<SubjectDTO> getByCountryId(Long countryId) {
        return repo.findByCountryId(countryId)
                .stream()
                .map(s -> new SubjectDTO(
                        s.getId(),
                        s.getName(),
                        s.getLevel() != null ? s.getLevel().getId() : null,
                        s.getLevel() != null ? s.getLevel().getName() : null,
                        (s.getLevel() != null && s.getLevel().getFiliere() != null)
                                ? s.getLevel().getFiliere().getId()
                                : null,
                        (s.getLevel() != null && s.getLevel().getFiliere() != null)
                                ? s.getLevel().getFiliere().getName()
                                : null
                ))
                .toList();
    }

    public Subject create(Subject s, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        s.setCountry(country);
        return repo.save(s);
    }

    public Subject update(Long id, Subject s) {
        s.setId(id);
        return repo.save(s);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}