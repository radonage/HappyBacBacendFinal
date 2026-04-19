package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.DTO.ExerciseDTO;
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
    public List<ExerciseDTO> getAll(@RequestParam Long countryId) {
        return service.getAll(countryId);
    }

    @GetMapping("/course/{courseId}")
    public List<ExerciseDTO> getByCourse(
            @PathVariable Long courseId,
            @RequestParam Long countryId
    ) {
        return service.getByCourse(courseId, countryId);
    }

    @PostMapping
    public ExerciseDTO create(
            @RequestBody ExerciseDTO dto,
            @RequestParam Long courseId,
            @RequestParam Long countryId
    ) {
        return service.create(dto, courseId, countryId);
    }

    @PutMapping("/{id}")
    public ExerciseDTO update(
            @PathVariable Long id,
            @RequestBody ExerciseDTO dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}