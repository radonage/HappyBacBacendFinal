package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.SubjectDTO;
import com.happyBackLast.happyBacklast.model.Subject;

import java.util.List;

public interface SubjectService {

    List<SubjectDTO> getByCountryId(Long countryId);

    Subject create(Subject subject, Long countryId);

    Subject update(Long id, Subject subject);

    void delete(Long id);
    List<SubjectDTO> getByCountryIdAndLevelId(Long countryId, Long levelId);
}