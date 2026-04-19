package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.LevelDTO;
import com.happyBackLast.happyBacklast.DTO.LevelResponseDTO;

import java.util.List;

public interface LevelService {

    List<LevelResponseDTO> getAll(Long countryId);

    LevelResponseDTO create(LevelDTO dto, Long countryId);

    LevelResponseDTO update(Long id, LevelDTO dto);

    void delete(Long id);
}