package com.happyBackLast.happyBacklast.service.serviceImpl;

import com.happyBackLast.happyBacklast.DTO.ExerciseDTO;
import com.happyBackLast.happyBacklast.DTO.mapper.ExerciseMapper;
import com.happyBackLast.happyBacklast.model.Course;
import com.happyBackLast.happyBacklast.model.Exercise;
import com.happyBackLast.happyBacklast.model.Country;
import com.happyBackLast.happyBacklast.repository.CourseRepository;
import com.happyBackLast.happyBacklast.repository.ExerciseRepository;
import com.happyBackLast.happyBacklast.repository.CountryRepository;
import com.happyBackLast.happyBacklast.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final CourseRepository courseRepository;
    private final CountryRepository countryRepository;

    public ExerciseServiceImpl(
            ExerciseRepository exerciseRepository,
            CourseRepository courseRepository,
            CountryRepository countryRepository
    ) {
        this.exerciseRepository = exerciseRepository;
        this.courseRepository = courseRepository;
        this.countryRepository = countryRepository;
    }



    public List<ExerciseDTO> getAll(Long countryId) {

        List<Exercise> list = (countryId != null)
                ? exerciseRepository.findAllByCountry(countryId)
                : exerciseRepository.findAll();

        return list.stream()
                .map(ExerciseMapper::toDTO)
                .toList();
    }

    public List<ExerciseDTO> getByCourse(Long courseId, Long countryId) {

        return exerciseRepository.findByCourseAndCountry(courseId, countryId)
                .stream()
                .map(ExerciseMapper::toDTO)
                .toList();
    }

    public ExerciseDTO getById(Long id) {

        Exercise e = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        return ExerciseMapper.toDTO(e);
    }

    public ExerciseDTO create(Exercise e, Long courseId, Long countryId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        e.setCourse(course);
        e.setCountry(country);

        Exercise saved = exerciseRepository.save(e);

        return ExerciseMapper.toDTO(saved);
    }

    public ExerciseDTO update(Long id, Exercise data) {

        Exercise existing = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        existing.setTitle(data.getTitle());
        existing.setStatement(data.getStatement());
        existing.setCorrection(data.getCorrection());
        existing.setVideoUrl(data.getVideoUrl());
        existing.setFileUrls(data.getFileUrls());

        return ExerciseMapper.toDTO(exerciseRepository.save(existing));
    }

    public void delete(Long id) {
        exerciseRepository.deleteById(id);
    }
}