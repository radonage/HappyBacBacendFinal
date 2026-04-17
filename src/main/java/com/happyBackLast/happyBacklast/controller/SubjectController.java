package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.dto.SubjectDTO;
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
    public List<SubjectDTO> getAll(@RequestParam(required = false) Long countryId) {
            System.out.println("Zabi : " + countryId);
        return service.getByCountryId(countryId);
        }


    @PostMapping
    public Subject create(
            @RequestBody Subject s,
            @RequestParam Long countryId
    ) {
        System.out.println("CREATE countryId => " + countryId);
        return service.create(s, countryId);
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