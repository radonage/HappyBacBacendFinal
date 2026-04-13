package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.dto.LevelDTO;
import com.happyBackLast.happyBacklast.model.Filiere;
import com.happyBackLast.happyBacklast.model.Level;
import com.happyBackLast.happyBacklast.repository.FiliereRepository;
import com.happyBackLast.happyBacklast.repository.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {

    private final LevelRepository levelRepository;
    private final FiliereRepository filiereRepository;

    public LevelService(LevelRepository levelRepository,
                        FiliereRepository filiereRepository) {
        this.levelRepository = levelRepository;
        this.filiereRepository = filiereRepository;
    }

    public List<Level> getAll() {
        return levelRepository.findAll();
    }

    public Level create(LevelDTO dto) {

        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        Level level = new Level();
        level.setName(dto.getName());
        level.setFiliere(filiere);

        return levelRepository.save(level);
    }

    public Level update(Long id, LevelDTO dto) {

        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level introuvable"));

        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        level.setName(dto.getName());
        level.setFiliere(filiere);

        return levelRepository.save(level);
    }

    public void delete(Long id) {
        levelRepository.deleteById(id);
    }
}