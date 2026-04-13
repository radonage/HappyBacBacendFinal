package com.happyBackLast.happyBacklast.controller;


import com.happyBackLast.happyBacklast.model.Course;
import com.happyBackLast.happyBacklast.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin("*")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Course create(@RequestBody Course c) {
        return service.create(c);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course c) {
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}