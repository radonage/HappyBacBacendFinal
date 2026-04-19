package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.DTO.ExamDTO;
import com.happyBackLast.happyBacklast.DTO.mapper.ExamMapper;
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
    public List<ExamDTO> getAll(@RequestParam Long countryId) {
        return service.getAllDto(countryId);
    }

    @PostMapping
    public ExamDTO create(
            @RequestBody ExamDTO dto,
            @RequestParam Long countryId
    ) {
        return ExamMapper.toDto(service.create(dto, countryId));
    }

    @PutMapping("/{id}")
    public ExamDTO update(
            @PathVariable Long id,
            @RequestBody ExamDTO dto,
            @RequestParam Long countryId
    ) {
        return ExamMapper.toDto(service.update(id, dto, countryId));
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            @RequestParam Long countryId
    ) {
        service.delete(id, countryId);
    }
}