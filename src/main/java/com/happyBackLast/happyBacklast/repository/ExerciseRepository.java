package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByCountryId(Long countryId);

    List<Exercise> findByCourseIdAndCountryId(Long courseId, Long countryId);
}