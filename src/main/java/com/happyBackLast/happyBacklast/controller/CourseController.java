package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.DTO.CourseDTO;
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
    public List<CourseDTO> getAll(@RequestParam(required = false) Long countryId) {
        if (countryId != null) {
            return service.getByCountryDto(countryId);
        }
        return service.getAllDto();
    }


    @GetMapping("/by-country-subject")
    public List<CourseDTO> getCourses(
            @RequestParam Long countryId,
            @RequestParam Long subjectId
    ) {
        return service.getByCountryAndSubject(countryId, subjectId);
    }
    @PostMapping
    public Course create(
            @RequestBody Course c,
            @RequestParam Long countryId
    ) {
        return service.create(c, countryId);
    }

    @PutMapping("/{id}")
    public Course update(
            @PathVariable Long id,
            @RequestBody Course c
    ) {
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}