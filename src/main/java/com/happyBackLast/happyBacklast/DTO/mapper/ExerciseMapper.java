package com.happyBackLast.happyBacklast.DTO.mapper;

import com.happyBackLast.happyBacklast.DTO.ExerciseDTO;
import com.happyBackLast.happyBacklast.model.Exercise;

import java.util.List;

public class ExerciseMapper {

    public static ExerciseDTO toDTO(Exercise e) {

        List<String> files = e.getFileUrls() == null ? List.of() : e.getFileUrls();

        return new ExerciseDTO(
                e.getId(),
                e.getTitle(),
                e.getStatement(),
                e.getCorrection(),
                e.getVideoUrl(),
                files,
                e.getCourse() != null ? e.getCourse().getId() : null,
                e.getCountry() != null ? e.getCountry().getId() : null
        );
    }

    public static List<ExerciseDTO> toDTOList(List<Exercise> list) {
        return list.stream().map(ExerciseMapper::toDTO).toList();
    }
}