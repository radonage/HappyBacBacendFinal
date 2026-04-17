package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.dto.ExamDTO;
import com.happyBackLast.happyBacklast.model.Exam;
import com.happyBackLast.happyBacklast.repository.ExamRepository;
import com.happyBackLast.happyBacklast.repository.SubjectRepository;
import com.happyBackLast.happyBacklast.repository.LevelRepository;
import com.happyBackLast.happyBacklast.repository.CountryRepository;
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

    // ---------------- GET BY COUNTRY ----------------
    public List<Exam> getAll(Long countryId) {
        return repo.findByCountryId(countryId);
    }

    public Exam create(ExamDTO dto, Long countryId) {

        System.out.println("📦 DTO = " + dto);
        System.out.println("🌍 countryId = " + countryId);

        Exam exam = new Exam();

        exam.setTitle(dto.title());
        exam.setType(dto.type());
        exam.setYear(String.valueOf(dto.year()));
        exam.setDescription(dto.description());
        exam.setCorrectionVideoUrl(dto.correctionVideoUrl());

        exam.setCountry(
                countryRepo.findById(countryId)
                        .orElseThrow(() -> new RuntimeException("Country not found"))
        );

        exam.setSubject(
                subjectRepo.findById(dto.subject().id())
                        .orElseThrow(() -> new RuntimeException("Subject not found"))
        );

        exam.setLevel(
                levelRepo.findById(dto.level().getId())
                        .orElseThrow(() -> new RuntimeException("Level not found"))
        );

        return repo.save(exam);
    }

    public Exam update(Long id, ExamDTO dto, Long countryId) {

        Exam exam = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        if (!exam.getCountry().getId().equals(countryId)) {
            throw new RuntimeException("Unauthorized country access");
        }

        exam.setTitle(dto.title());
        exam.setType(dto.type());
        exam.setYear(String.valueOf(dto.year()));
        exam.setDescription(dto.description());
        exam.setCorrectionVideoUrl(dto.correctionVideoUrl());

        exam.setSubject(
                subjectRepo.findById(dto.subject().id())
                        .orElseThrow(() -> new RuntimeException("Subject not found"))
        );

        exam.setLevel(
                levelRepo.findById(dto.level().getId())
                        .orElseThrow(() -> new RuntimeException("Level not found"))
        );

        return repo.save(exam);
    }

    public void delete(Long id, Long countryId) {

        Exam exam = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        if (!exam.getCountry().getId().equals(countryId)) {
            throw new RuntimeException("Unauthorized country access");
        }

        repo.delete(exam);
    }
}