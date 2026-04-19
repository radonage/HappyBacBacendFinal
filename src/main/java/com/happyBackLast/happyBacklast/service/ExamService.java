package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.ExamDTO;
import com.happyBackLast.happyBacklast.model.Exam;

import java.util.List;

public interface ExamService {

    List<ExamDTO> getAllDto(Long countryId);

    Exam create(ExamDTO dto, Long countryId);

    Exam update(Long id, ExamDTO dto, Long countryId);

    void delete(Long id, Long countryId);

}