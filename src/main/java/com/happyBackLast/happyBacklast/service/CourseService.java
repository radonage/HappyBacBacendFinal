package com.happyBackLast.happyBacklast.service;


import com.happyBackLast.happyBacklast.model.Course;
import com.happyBackLast.happyBacklast.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getAll() {
        return repo.findAll();
    }

    public Course create(Course c) {
        return repo.save(c);
    }

    public Course update(Long id, Course c) {
        c.setId(id);
        return repo.save(c);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}