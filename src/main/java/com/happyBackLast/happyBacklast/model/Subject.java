package com.happyBackLast.happyBacklast.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity

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

    public Subject(Long id, Country country, String name, Level level, List<Course> courses) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.level = level;
        this.courses = courses;
    }

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}