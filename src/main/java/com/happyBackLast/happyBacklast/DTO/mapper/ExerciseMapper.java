package com.happyBackLast.happyBacklast.DTO.mapper;

import com.happyBackLast.happyBacklast.DTO.DocumentDTO;
import com.happyBackLast.happyBacklast.DTO.ExerciseDTO;
import com.happyBackLast.happyBacklast.model.Exercise;

import java.util.List;

public class ExerciseMapper {

    public static ExerciseDTO toDto(Exercise e) {
        return new ExerciseDTO(
                e.getId(),
                e.getTitle(),
                e.getStatement(),
                e.getCorrection(),
                e.getVideoUrl(),
                e.getFileUrls()
                        .stream()
                        .map(url -> new DocumentDTO(url, null, null))
                        .toList(),
                e.getCourse().getId(),
                e.getCountry().getId()
        );
    }
}