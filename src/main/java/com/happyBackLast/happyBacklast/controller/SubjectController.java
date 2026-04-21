package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.DTO.SubjectDTO;
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

    // 🔥 EXISTING: filter by country only (optional)
    @GetMapping
    public List<SubjectDTO> getAll(@RequestParam(required = false) Long countryId) {
        return service.getByCountryId(countryId);
    }

    // ✅ NEW: filter by country + level
    @GetMapping("/filter")
    public List<SubjectDTO> getByCountryAndLevel(
            @RequestParam Long countryId,
            @RequestParam Long levelId
    ) {
        System.out.println("📡 FILTER SUBJECTS");
        System.out.println("countryId = " + countryId);
        System.out.println("levelId = " + levelId);

        return service.getByCountryIdAndLevelId(countryId, levelId);
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