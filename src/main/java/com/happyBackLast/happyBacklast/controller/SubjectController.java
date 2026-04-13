package com.happyBackLast.happyBacklast.controller;
import com.happyBackLast.happyBacklast.model.Subject;
import com.happyBackLast.happyBacklast.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin("*")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<Subject> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Subject create(@RequestBody Subject s) {
        return service.create(s);
    }

    @PutMapping("/{id}")
    public Subject update(@PathVariable Long id, @RequestBody Subject s) {
        return service.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
