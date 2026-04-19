package com.happyBackLast.happyBacklast.dto;

import java.util.List;

public record ExamDTO(
        Long id,
        String title,
        String type,
        String year,
        String description,
        String correctionVideoUrl,
        SubjectDTO subject,
        LevelDTO level,
        List<DocumentDTO> documents
) {}