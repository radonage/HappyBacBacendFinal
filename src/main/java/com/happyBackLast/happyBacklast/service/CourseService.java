package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.CourseDTO;
import com.happyBackLast.happyBacklast.model.Course;

import java.util.List;

public interface CourseService {

    Course create(Course course, Long countryId);

    Course update(Long id, Course course);

    List<CourseDTO> getAllDto();

    List<CourseDTO> getByCountryDto(Long countryId);

    void delete(Long id);
}