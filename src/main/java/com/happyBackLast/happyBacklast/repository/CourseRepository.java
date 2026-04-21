package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    long count();
    long countByCountry_Id(Long countryId);

    @Query("""
        SELECT DISTINCT c
        FROM Course c
        LEFT JOIN FETCH c.fileUrls
        LEFT JOIN FETCH c.subject s
        LEFT JOIN FETCH s.level
        WHERE c.country.id = :countryId
    """)
    List<Course> findByCountryId(@Param("countryId") Long countryId);

    @Query("""
        SELECT DISTINCT c
        FROM Course c
        LEFT JOIN FETCH c.fileUrls
        LEFT JOIN FETCH c.subject s
        LEFT JOIN FETCH s.level
    """)
    List<Course> findAllWithFiles();

    @Query(value = """
        SELECT TO_CHAR(c.created_at, 'YYYY-MM'), COUNT(*)
        FROM course c
        WHERE c.country_id = :countryId
        GROUP BY TO_CHAR(c.created_at, 'YYYY-MM')
        ORDER BY TO_CHAR(c.created_at, 'YYYY-MM')
    """, nativeQuery = true)
    List<Object[]> countCoursesByMonth(@Param("countryId") Long countryId);

    @Query("""
select c from Course c
join fetch c.country
join fetch c.subject
left join fetch c.fileUrls
where c.country.id = :countryId
and c.subject.id = :subjectId
""")
    List<Course> findByCountryAndSubject(Long countryId, Long subjectId);
}