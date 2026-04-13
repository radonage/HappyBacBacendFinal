package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    @Query("SELECT COUNT(f) FROM Filiere f")
    long countFilieres();
}