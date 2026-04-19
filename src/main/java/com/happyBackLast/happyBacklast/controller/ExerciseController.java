package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.dto.ExerciseDTO;
import com.happyBackLast.happyBacklast.model.Exercise;
import com.happyBackLast.happyBacklast.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin("*")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExerciseDTO> getAll(@RequestParam(required = false) Long countryId) {
        return service.getAll(countryId);
    }

    @GetMapping("/course/{courseId}")
    public List<ExerciseDTO> getByCourse(
            @PathVariable Long courseId,
            @RequestParam Long countryId
    ) {
        return service.getByCourse(courseId, countryId);
    }

    @GetMapping("/{id}")
    public ExerciseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ExerciseDTO create(
            @RequestBody Exercise e,
            @RequestParam Long courseId,
            @RequestParam Long countryId
    ) {
        System.out.println("CREATE EXERCISE courseId => " + courseId);
        return service.create(e, courseId, countryId);
    }

    @PutMapping("/{id}")
    public ExerciseDTO update(
            @PathVariable Long id,
            @RequestBody Exercise e
    ) {
        return service.update(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}