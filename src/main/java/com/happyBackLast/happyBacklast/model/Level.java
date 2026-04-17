package com.happyBackLast.happyBacklast.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String name;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    @JsonIgnoreProperties("levels")
    private Filiere filiere;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("level")
    private List<Subject> subjects;
}