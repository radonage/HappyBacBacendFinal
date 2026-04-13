package com.happyBackLast.happyBacklast.repository;


import com.happyBackLast.happyBacklast.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT COUNT(c) FROM Course c")
    long countCourses();

    @Query("""
        SELECT TO_CHAR(c.createdAt, 'YYYY-MM'), COUNT(c)
        FROM Course c
        GROUP BY TO_CHAR(c.createdAt, 'YYYY-MM')
        ORDER BY TO_CHAR(c.createdAt, 'YYYY-MM')
    """)
    List<Object[]> countCoursesByMonth();

}