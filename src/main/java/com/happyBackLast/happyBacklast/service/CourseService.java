package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.dto.CourseDTO;
import com.happyBackLast.happyBacklast.model.Country;
import com.happyBackLast.happyBacklast.model.Course;
import com.happyBackLast.happyBacklast.repository.CountryRepository;
import com.happyBackLast.happyBacklast.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repo;
    private final CountryRepository countryRepository;
    public CourseService(CourseRepository repo, CountryRepository countryRepository) {
        this.repo = repo;
        this.countryRepository = countryRepository;
    }

    // ✅ CORRECT
    public List<Course> getByCountry(Long countryId) {
        return repo.findByCountry_Id(countryId);
    }

    public List<Course> getAll() {
        return repo.findAll();
    }

    public Course create(Course c, Long countryId) {

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        c.setCountry(country);

        return repo.save(c);
    }

    public Course update(Long id, Course c) {
        c.setId(id);
        return repo.save(c);
    }
    public List<CourseDTO> getAllDto() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<CourseDTO> getByCountryDto(Long countryId) {
        return repo.findByCountryId(countryId)
                .stream()
                .map(this::toDto)
                .toList();
    }
    private CourseDTO toDto(Course c) {
        return new CourseDTO(
                c.getId(),
                c.getTitle(),

                c.getChapter(),

                c.getDescription(),
                c.getVideoUrl(),
                c.getCreatedAt() != null ? c.getCreatedAt().toString() : null,

                c.getSubject() != null ? c.getSubject().getId() : null,
                c.getSubject() != null ? c.getSubject().getName() : null,

                c.getSubject() != null && c.getSubject().getLevel() != null
                        ? c.getSubject().getLevel().getId()
                        : null,

                c.getSubject() != null && c.getSubject().getLevel() != null
                        ? c.getSubject().getLevel().getName()
                        : null
        );
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}