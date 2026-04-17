package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.dto.ExamDTO;
import com.happyBackLast.happyBacklast.model.Exam;
import com.happyBackLast.happyBacklast.service.ExamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin("*")
public class ExamController {

    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @GetMapping
    public List<Exam> getAll(@RequestParam Long countryId) {
        return service.getAll(countryId);
    }

    @PostMapping
    public Exam create(
            @RequestBody ExamDTO dto,
            @RequestParam Long countryId
    ) {
        return service.create(dto, countryId);
    }

    @PutMapping("/{id}")
    public Exam update(
            @PathVariable Long id,
            @RequestBody ExamDTO dto,
            @RequestParam Long countryId
    ) {
        return service.update(id, dto, countryId);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            @RequestParam Long countryId
    ) {
        service.delete(id, countryId);
    }
}