package com.happyBackLast.happyBacklast.DTO.mapper;

import com.happyBackLast.happyBacklast.DTO.CourseDTO;
import com.happyBackLast.happyBacklast.model.Course;

import java.util.List;

public class CourseMapper {

    public static CourseDTO toDto(Course c) {

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

    public static List<CourseDTO> toDTOList(List<Course> list) {
        return list.stream().map(CourseMapper::toDto).toList();
    }
}