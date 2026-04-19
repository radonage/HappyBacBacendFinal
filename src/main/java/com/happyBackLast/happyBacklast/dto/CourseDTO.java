package com.happyBackLast.happyBacklast.dto;

import java.util.List;

public record CourseDTO(
        Long id,
        String title,
        String chapter,
        String description,
        String videoUrl,
        String createdAt,
        Long subjectId,
        String subjectName,
        Long levelId,
        String levelName,
        List<String> fileUrls
) {}