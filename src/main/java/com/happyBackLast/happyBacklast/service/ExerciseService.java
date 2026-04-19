package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.ExerciseDTO;
import com.happyBackLast.happyBacklast.model.Exercise;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDTO> getAll(Long countryId);

    List<ExerciseDTO> getByCourse(Long courseId, Long countryId);

    ExerciseDTO getById(Long id);

    ExerciseDTO create(Exercise exercise, Long courseId, Long countryId);

    ExerciseDTO update(Long id, Exercise exercise);

    void delete(Long id);
}