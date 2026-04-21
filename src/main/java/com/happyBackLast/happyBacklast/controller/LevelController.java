package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.DTO.LevelDTO;
import com.happyBackLast.happyBacklast.DTO.LevelResponseDTO;
import com.happyBackLast.happyBacklast.service.LevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
@CrossOrigin("*")
public class LevelController {

    private final LevelService service;

    public LevelController(LevelService service) {
        this.service = service;
    }

    @GetMapping
    public List<LevelResponseDTO> getAll(
            @RequestParam(required = false) Long countryId) {
        return service.getAll(countryId);
    }

    @GetMapping("/filter")
    public List<LevelResponseDTO> getByCountryAndFiliere(
            @RequestParam Long countryId,
            @RequestParam Long filiereId
    ) {
        return service.getByCountryAndFiliere(countryId, filiereId);
    }

    @PostMapping
    public LevelResponseDTO create(
            @RequestBody LevelDTO dto,
            @RequestParam Long countryId) {

        return service.create(dto, countryId);
    }

    @PutMapping("/{id}")
    public LevelResponseDTO update(
            @PathVariable Long id,
            @RequestBody LevelDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}