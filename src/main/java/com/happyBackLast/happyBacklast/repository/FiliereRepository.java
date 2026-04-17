package com.happyBackLast.happyBacklast.repository;

import com.happyBackLast.happyBacklast.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {

    long count();

    long countByCountry_Id(Long countryId);

    @Query("SELECT f FROM Filiere f WHERE f.country.id = :countryId")
    List<Filiere> findFilieresByCountryId(@Param("countryId") Long countryId);
}