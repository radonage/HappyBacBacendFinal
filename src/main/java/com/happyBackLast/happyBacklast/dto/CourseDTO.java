package com.happyBackLast.happyBacklast.dto;

public record CourseDTO(
        Long id,
        String title,
        String chapter, // ✅ AJOUT ICI
        String description,
        String videoUrl,
        String createdAt,

        Long subjectId,
        String subjectName,

        Long levelId,
        String levelName
) {}