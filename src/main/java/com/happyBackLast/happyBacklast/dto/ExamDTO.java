package com.happyBackLast.happyBacklast.dto;

import com.happyBackLast.happyBacklast.dto.LevelDTO;
import com.happyBackLast.happyBacklast.dto.SubjectDTO;

public record ExamDTO(
        String title,
        String type,
        String year,
        String description,
        String correctionVideoUrl,
        SubjectDTO subject,
        LevelDTO level
) {}