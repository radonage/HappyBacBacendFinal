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

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String title;
    private String chapter;
    private String description;

    private String videoUrl;

    @ElementCollection
    private List<String> fileUrls;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties({"courses", "level"})
    private Subject subject;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}