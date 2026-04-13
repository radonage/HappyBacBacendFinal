package com.happyBackLast.happyBacklast.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties({"courses", "level"})
    private Subject subject;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("course")
    private List<Chapter> chapters;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}