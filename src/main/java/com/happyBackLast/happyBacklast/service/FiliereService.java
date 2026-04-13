package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.dto.FiliereRequest;
import com.happyBackLast.happyBacklast.model.Filiere;
import com.happyBackLast.happyBacklast.repository.FiliereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {

    private final FiliereRepository filiereRepository;

    public FiliereService(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    public List<Filiere> getAll() {
        return filiereRepository.findAll();
    }

    public Filiere create(FiliereRequest request) {
        Filiere f = new Filiere();
        f.setName(request.getName());
        return filiereRepository.save(f);
    }

    public Filiere update(Long id, FiliereRequest request) {
        Filiere f = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        f.setName(request.getName());
        return filiereRepository.save(f);
    }

    public void delete(Long id) {
        filiereRepository.deleteById(id);
    }
}