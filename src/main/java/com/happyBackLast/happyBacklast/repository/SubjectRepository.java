package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    long count();

    long countByCountry_Id(Long countryId);

    @Query(value = "SELECT * FROM subject WHERE country_id = :countryId", nativeQuery = true)
    List<Subject> findByCountryId(@Param("countryId") Long countryId);
    @Query("""
        SELECT s.name, COUNT(c)
        FROM Course c
        JOIN c.subject s
        WHERE c.country.id = :countryId
        GROUP BY s.name
    """)
    List<Object[]> countCoursesBySubject(@Param("countryId") Long countryId);
}