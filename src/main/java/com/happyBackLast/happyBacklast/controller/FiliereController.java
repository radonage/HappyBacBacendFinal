package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.dto.FiliereDTO;
import com.happyBackLast.happyBacklast.dto.FiliereRequest;
import com.happyBackLast.happyBacklast.model.Filiere;
import com.happyBackLast.happyBacklast.service.FiliereService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filieres")
@CrossOrigin("*")
public class FiliereController {

    private final FiliereService service;

    public FiliereController(FiliereService service) {
        this.service = service;
    }


    @GetMapping
    public List<FiliereDTO> getByCountry(@RequestParam Long countryId) {
        return service.getByCountry(countryId)
                .stream()
                .map(f -> new FiliereDTO(f.getId(), f.getName(), f.getCountry().getId()))
                .toList();
    }

    @PostMapping
    public Filiere create(@RequestBody FiliereRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public Filiere update(@PathVariable Long id,
                          @RequestBody FiliereRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}