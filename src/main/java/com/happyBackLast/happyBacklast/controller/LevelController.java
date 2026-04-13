package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.dto.LevelDTO;
import com.happyBackLast.happyBacklast.model.Level;
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
    public List<Level> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Level create(@RequestBody LevelDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Level update(@PathVariable Long id,
                        @RequestBody LevelDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}