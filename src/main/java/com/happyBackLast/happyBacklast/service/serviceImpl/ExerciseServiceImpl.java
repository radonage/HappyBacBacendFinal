package com.happyBackLast.happyBacklast.service.serviceImpl;

import com.happyBackLast.happyBacklast.DTO.*;
import com.happyBackLast.happyBacklast.DTO.mapper.ExerciseMapper;
import com.happyBackLast.happyBacklast.model.Exercise;
import com.happyBackLast.happyBacklast.repository.*;
import com.happyBackLast.happyBacklast.service.ExerciseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository repo;
    private final CourseRepository courseRepo;
    private final CountryRepository countryRepo;

    public ExerciseServiceImpl(
            ExerciseRepository repo,
            CourseRepository courseRepo,
            CountryRepository countryRepo
    ) {
        this.repo = repo;
        this.courseRepo = courseRepo;
        this.countryRepo = countryRepo;
    }

    @Transactional
    public List<ExerciseDTO> getAll(Long countryId) {
        return repo.findByCountryId(countryId)
                .stream()
                .map(ExerciseMapper::toDto)
                .toList();
    }

    public List<ExerciseDTO> getByCourse(Long courseId, Long countryId) {
        return repo.findByCourseIdAndCountryId(courseId, countryId)
                .stream()
                .map(ExerciseMapper::toDto)
                .toList();
    }

    public ExerciseDTO getById(Long id) {
        return ExerciseMapper.toDto(
                repo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Exercise not found"))
        );
    }

    public ExerciseDTO create(ExerciseDTO dto, Long courseId, Long countryId) {

        Exercise e = new Exercise();

        e.setTitle(dto.title());
        e.setStatement(dto.statement());
        e.setCorrection(dto.correction());
        e.setVideoUrl(dto.videoUrl());

        e.setCourse(
                courseRepo.findById(courseId)
                        .orElseThrow(() -> new RuntimeException("Course not found"))
        );

        e.setCountry(
                countryRepo.findById(countryId)
                        .orElseThrow(() -> new RuntimeException("Country not found"))
        );

        if (dto.documents() != null) {
            e.setFileUrls(
                    dto.documents()
                            .stream()
                            .map(DocumentDTO::getUrl)
                            .toList()
            );
        }

        return ExerciseMapper.toDto(repo.save(e));
    }

    public ExerciseDTO update(Long id, ExerciseDTO dto) {

        Exercise e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        e.setTitle(dto.title());
        e.setStatement(dto.statement());
        e.setCorrection(dto.correction());
        e.setVideoUrl(dto.videoUrl());

        if (dto.documents() != null) {
            e.setFileUrls(
                    dto.documents()
                            .stream()
                            .map(DocumentDTO::getUrl)
                            .toList()
            );
        }

        return ExerciseMapper.toDto(repo.save(e));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}