package com.happyBackLast.happyBacklast.DTO.mapper;

import com.happyBackLast.happyBacklast.DTO.DocumentDTO;
import com.happyBackLast.happyBacklast.DTO.ExamDTO;
import com.happyBackLast.happyBacklast.DTO.LevelDTO;
import com.happyBackLast.happyBacklast.DTO.SubjectDTO;
import com.happyBackLast.happyBacklast.model.Exam;

import java.util.List;

public class ExamMapper {

    public static ExamDTO toDto(Exam e) {
        return new ExamDTO(
                e.getId(),
                e.getTitle(),
                e.getType(),
                e.getYear(),
                e.getDescription(),
                e.getCorrectionVideoUrl(),

                e.getSubject() != null
                        ? new SubjectDTO(
                        e.getSubject().getId(),
                        e.getSubject().getName(),
                        null,
                        null,
                        null,
                        null
                )
                        : null,

                e.getLevel() != null
                        ? new LevelDTO(
                        e.getLevel().getId(),
                        e.getLevel().getName(),
                        null
                )
                        : null,

                e.getFileUrls() != null
                        ? e.getFileUrls().stream()
                          .map(url -> new DocumentDTO(url, null, null))
                          .toList()
                        : List.of()
        );
    }

}
