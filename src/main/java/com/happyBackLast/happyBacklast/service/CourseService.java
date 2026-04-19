package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.dto.CourseDTO;
import com.happyBackLast.happyBacklast.dto.DocumentDTO;
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

    public Course create(Course c, Long countryId) {

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        c.setCountry(country);

        if (c.getDocuments() != null && !c.getDocuments().isEmpty()) {

            List<String> urls = c.getDocuments()
                    .stream()
                    .map(DocumentDTO::getUrl)
                    .toList();

            c.setFileUrls(urls);

        } else {
            c.setFileUrls(List.of());
        }

        return repo.save(c);
    }


    public Course update(Long id, Course c) {
        c.setId(id);
        return repo.save(c);
    }

    public List<CourseDTO> getAllDto() {

        System.out.println("🔥 GET ALL COURSES START");

        List<Course> courses = repo.findAllWithFiles();

        System.out.println("📦 TOTAL COURSES: " + courses.size());

        return courses.stream()
                .map(this::toDto)
                .toList();
    }

    public List<CourseDTO> getByCountryDto(Long countryId) {

        return repo.findByCountryId(countryId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public CourseDTO toDto(Course c) {

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
                        : null,

                c.getFileUrls()
        );
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}