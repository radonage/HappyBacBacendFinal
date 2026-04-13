package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.model.Subject;
import com.happyBackLast.happyBacklast.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository repo;

    public SubjectService(SubjectRepository repo) {
        this.repo = repo;
    }

    public List<Subject> getAll() {
        return repo.findAll();
    }

    public Subject create(Subject s) {
        return repo.save(s);
    }

    public Subject update(Long id, Subject s) {
        s.setId(id);
        return repo.save(s);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}