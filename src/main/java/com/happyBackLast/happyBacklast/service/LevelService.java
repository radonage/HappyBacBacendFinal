package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.LevelDTO;
import com.happyBackLast.happyBacklast.DTO.LevelResponseDTO;

import java.util.List;

public interface LevelService {

    // 📌 ancien (peut rester si tu l’utilises encore)
    List<LevelResponseDTO> getAll(Long countryId);

    // 🔥 NOUVEAU : filtre avancé (country + filiere)
    List<LevelResponseDTO> getByCountryAndFiliere(Long countryId, Long filiereId);

    LevelResponseDTO create(LevelDTO dto, Long countryId);

    LevelResponseDTO update(Long id, LevelDTO dto);

    void delete(Long id);
}