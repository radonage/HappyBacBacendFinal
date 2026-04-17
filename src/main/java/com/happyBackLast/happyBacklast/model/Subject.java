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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    private String name;

    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonIgnoreProperties({"subjects", "filiere"})
    private Level level;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("subject")
    private List<Course> courses;
}