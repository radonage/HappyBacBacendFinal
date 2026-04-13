package com.happyBackLast.happyBacklast.service;


import com.happyBackLast.happyBacklast.model.Exam;
import com.happyBackLast.happyBacklast.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository repo;

    public ExamService(ExamRepository repo) {
        this.repo = repo;
    }

    public List<Exam> getAll() {
        return repo.findAll();
    }

    public Exam create(Exam e) {
        return repo.save(e);
    }

    public Exam update(Long id, Exam e) {
        e.setId(id);
        return repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}