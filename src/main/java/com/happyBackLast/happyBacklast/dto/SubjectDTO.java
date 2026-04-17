package com.happyBackLast.happyBacklast.dto;

public record SubjectDTO(
        Long id,
        String name,
        Long levelId,
        String levelName,
        Long filiereId,
        String filiereName
) {}