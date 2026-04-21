package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {

    long count();

    long countByCountry_Id(Long countryId);

    @Query(value = "SELECT * FROM level l WHERE l.country_id = :countryId", nativeQuery = true)
    List<Level> findByCountryIdNative(@Param("countryId") Long countryId);

    List<Level> findByCountryIdAndFiliereId(Long countryId, Long filiereId);
}