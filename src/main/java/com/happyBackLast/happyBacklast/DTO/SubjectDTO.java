package com.happyBackLast.happyBacklast.DTO;

public record SubjectDTO(
        Long id,
        String name,
        Long levelId,
        String levelName,
        Long filiereId,
        String filiereName
) {}