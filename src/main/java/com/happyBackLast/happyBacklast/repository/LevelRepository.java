package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LevelRepository extends JpaRepository<Level, Long> {
    @Query("SELECT COUNT(l) FROM Level l")
    long countLevels();
}