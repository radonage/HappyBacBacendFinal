package com.happyBackLast.happyBacklast.DTO;

import java.util.List;

public record ExerciseDTO(
        Long id,
        String title,
        String statement,
        String correction,
        String videoUrl,
        List<String> fileUrls,
        Long courseId,
        Long countryId

) {}