package com.happyBackLast.happyBacklast.service.serviceImpl;

import com.happyBackLast.happyBacklast.DTO.CourseDTO;
import com.happyBackLast.happyBacklast.DTO.mapper.CourseMapper;
import com.happyBackLast.happyBacklast.model.Country;
import com.happyBackLast.happyBacklast.model.Course;
import com.happyBackLast.happyBacklast.repository.CountryRepository;
import com.happyBackLast.happyBacklast.repository.CourseRepository;
import com.happyBackLast.happyBacklast.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;
    private final CountryRepository countryRepository;

    public CourseServiceImpl(CourseRepository repo, CountryRepository countryRepository) {
        this.repo = repo;
        this.countryRepository = countryRepository;
    }

    public Course create(Course c, Long countryId) {

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        c.setCountry(country);
        System.out.println("FILE URLS REÇUES = " + c.getFileUrls());

        if (c.getFileUrls() == null) {
            c.setFileUrls(List.of());
        }

        return repo.save(c);
    }


    public Course update(Long id, Course c) {

        Course existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existing.setTitle(c.getTitle());
        existing.setChapter(c.getChapter());
        existing.setDescription(c.getDescription());
        existing.setVideoUrl(c.getVideoUrl());

        if (c.getFileUrls() != null) {
            existing.setFileUrls(c.getFileUrls());
        }

        existing.setSubject(c.getSubject());

        return repo.save(existing);
    }

    public List<CourseDTO> getAllDto() {

        List<Course> courses = repo.findAllWithFiles();

        return courses.stream()
                .map(CourseMapper::toDto)
                .toList();
    }

    public List<CourseDTO> getByCountryDto(Long countryId) {

        return repo.findByCountryId(countryId)
                .stream()
                .map(CourseMapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}