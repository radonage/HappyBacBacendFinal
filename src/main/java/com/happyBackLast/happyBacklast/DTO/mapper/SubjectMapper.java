package com.happyBackLast.happyBacklast.DTO.mapper;

import com.happyBackLast.happyBacklast.DTO.SubjectDTO;
import com.happyBackLast.happyBacklast.model.Subject;

import java.util.List;

public class SubjectMapper {

    public static SubjectDTO toDTO(Subject s) {
        return new SubjectDTO(
                s.getId(),
                s.getName(),
                s.getLevel() != null ? s.getLevel().getId() : null,
                s.getLevel() != null ? s.getLevel().getName() : null,
                (s.getLevel() != null && s.getLevel().getFiliere() != null)
                        ? s.getLevel().getFiliere().getId()
                        : null,
                (s.getLevel() != null && s.getLevel().getFiliere() != null)
                        ? s.getLevel().getFiliere().getName()
                        : null
        );
    }

    public static List<SubjectDTO> toDTOList(List<Subject> list) {
        return list.stream().map(SubjectMapper::toDTO).toList();
    }
}