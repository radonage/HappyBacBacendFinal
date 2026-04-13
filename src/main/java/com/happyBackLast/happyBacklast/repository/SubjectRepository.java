package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT COUNT(s) FROM Subject s")
    long countSubjects();

    @Query("SELECT s.name, COUNT(c) FROM Course c JOIN c.subject s GROUP BY s.name")
    List<Object[]> countCoursesBySubject();

}