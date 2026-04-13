package com.happyBackLast.happyBacklast.controller;

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
    public List<Exam> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Exam create(@RequestBody Exam e) {
        return service.create(e);
    }

    @PutMapping("/{id}")
    public Exam update(@PathVariable Long id, @RequestBody Exam e) {
        return service.update(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}