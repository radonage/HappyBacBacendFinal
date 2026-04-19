package com.happyBackLast.happyBacklast.DTO.mapper;

import com.happyBackLast.happyBacklast.DTO.LevelResponseDTO;
import com.happyBackLast.happyBacklast.model.Level;

public class LevelMapper {

    public static LevelResponseDTO toDTO(Level level) {
        return new LevelResponseDTO(
                level.getId(),
                level.getName(),
                level.getFiliere().getId(),
                level.getFiliere().getName(),
                level.getCountry() != null ? level.getCountry().getId() : null,
                level.getCountry() != null ? level.getCountry().getName() : null
        );
    }
}
