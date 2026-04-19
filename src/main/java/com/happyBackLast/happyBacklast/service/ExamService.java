package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.dto.*;
import com.happyBackLast.happyBacklast.model.Exam;
import com.happyBackLast.happyBacklast.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository repo;
    private final SubjectRepository subjectRepo;
    private final LevelRepository levelRepo;
    private final CountryRepository countryRepo;

    public ExamService(
            ExamRepository repo,
            SubjectRepository subjectRepo,
            LevelRepository levelRepo,
            CountryRepository countryRepo
    ) {
        this.repo = repo;
        this.subjectRepo = subjectRepo;
        this.levelRepo = levelRepo;
        this.countryRepo = countryRepo;
    }

    @Transactional
    public List<ExamDTO> getAllDto(Long countryId) {
        return repo.findByCountryId(countryId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Exam create(ExamDTO dto, Long countryId) {

        Exam exam = new Exam();

        exam.setTitle(dto.title());
        exam.setType(dto.type());
        exam.setYear(dto.year());
        exam.setDescription(dto.description());
        exam.setCorrectionVideoUrl(dto.correctionVideoUrl());

        exam.setCountry(
                countryRepo.findById(countryId)
                        .orElseThrow(() -> new RuntimeException("Country not found"))
        );

        if (dto.subject() == null || dto.subject().id() == null) {
            throw new RuntimeException("Subject required");
        }

        exam.setSubject(
                subjectRepo.findById(dto.subject().id())
                        .orElseThrow(() -> new RuntimeException("Subject not found"))
        );

        if (dto.level() == null || dto.level().getId() == null) {
            throw new RuntimeException("Level required");
        }

        exam.setLevel(
                levelRepo.findById(dto.level().getId())
                        .orElseThrow(() -> new RuntimeException("Level not found"))
        );

        if (dto.documents() != null && !dto.documents().isEmpty()) {
            exam.setFileUrls(
                    dto.documents()
                            .stream()
                            .map(DocumentDTO::getUrl)
                            .toList()
            );
        } else {
            exam.setFileUrls(List.of());
        }

        return repo.save(exam);
    }

    public Exam update(Long id, ExamDTO dto, Long countryId) {

        Exam exam = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        if (!exam.getCountry().getId().equals(countryId)) {
            throw new RuntimeException("Unauthorized");
        }

        exam.setTitle(dto.title());
        exam.setType(dto.type());
        exam.setYear(dto.year());
        exam.setDescription(dto.description());
        exam.setCorrectionVideoUrl(dto.correctionVideoUrl());

        if (dto.subject() != null && dto.subject().id() != null) {
            exam.setSubject(
                    subjectRepo.findById(dto.subject().id())
                            .orElseThrow(() -> new RuntimeException("Subject not found"))
            );
        }

        if (dto.level() != null && dto.level().getId() != null) {
            exam.setLevel(
                    levelRepo.findById(dto.level().getId())
                            .orElseThrow(() -> new RuntimeException("Level not found"))
            );
        }

        if (dto.documents() != null) {
            exam.setFileUrls(
                    dto.documents()
                            .stream()
                            .map(DocumentDTO::getUrl)
                            .toList()
            );
        }

        return repo.save(exam);
    }

    public void delete(Long id, Long countryId) {

        Exam exam = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        if (!exam.getCountry().getId().equals(countryId)) {
            throw new RuntimeException("Unauthorized");
        }

        repo.delete(exam);
    }

    public ExamDTO toDto(Exam e) {
        return new ExamDTO(
                e.getId(),
                e.getTitle(),
                e.getType(),
                e.getYear(),
                e.getDescription(),
                e.getCorrectionVideoUrl(),

                e.getSubject() != null
                        ? new SubjectDTO(
                        e.getSubject().getId(),
                        e.getSubject().getName(),
                        null,
                        null,
                        null,
                        null
                )
                        : null,

                e.getLevel() != null
                        ? new LevelDTO(
                        e.getLevel().getId(),
                        e.getLevel().getName(),
                        null
                )
                        : null,

                e.getFileUrls() != null
                        ? e.getFileUrls().stream()
                          .map(url -> new DocumentDTO(url, null, null))
                          .toList()
                        : List.of()
        );
    }
}