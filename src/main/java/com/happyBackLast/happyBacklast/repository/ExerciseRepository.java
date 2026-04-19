package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e FROM Exercise e WHERE e.country.id = :countryId")
    List<Exercise> findAllByCountry(@Param("countryId") Long countryId);

    @Query("SELECT e FROM Exercise e WHERE e.course.id = :courseId AND e.country.id = :countryId")
    List<Exercise> findByCourseAndCountry(
            @Param("courseId") Long courseId,
            @Param("countryId") Long countryId
    );
}