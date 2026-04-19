package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.ExerciseDTO;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDTO> getAll(Long countryId);

    List<ExerciseDTO> getByCourse(Long courseId, Long countryId);

    ExerciseDTO getById(Long id);

    ExerciseDTO create(ExerciseDTO dto, Long courseId, Long countryId);

    ExerciseDTO update(Long id, ExerciseDTO dto);

    void delete(Long id);
}