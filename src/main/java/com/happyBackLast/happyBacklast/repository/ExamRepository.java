package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = """
        SELECT *
        FROM exam
        WHERE country_id = :countryId
    """, nativeQuery = true)
    List<Exam> findByCountryId(@Param("countryId") Long countryId);
}