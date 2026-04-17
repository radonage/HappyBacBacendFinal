package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    long count();
    @Query(value = "SELECT * FROM course WHERE country_id = :countryId", nativeQuery = true)
    List<Course> findByCountryId(@Param("countryId") Long countryId);
    List<Course> findByCountry_Id(Long countryId);

    long countByCountry_Id(Long countryId);

    @Query(value = """
        SELECT TO_CHAR(c.created_at, 'YYYY-MM'), COUNT(*)
        FROM course c
        WHERE c.country_id = :countryId
        GROUP BY TO_CHAR(c.created_at, 'YYYY-MM')
        ORDER BY TO_CHAR(c.created_at, 'YYYY-MM')
    """, nativeQuery = true)
    List<Object[]> countCoursesByMonth(@Param("countryId") Long countryId);
}