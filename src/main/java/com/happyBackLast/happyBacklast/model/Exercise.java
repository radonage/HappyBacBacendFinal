package com.happyBackLast.happyBacklast.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.happyBackLast.happyBacklast.DTO.DocumentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String statement;

    @Lob
    private String correction;

    private String videoUrl;

    @Transient
    private List<DocumentDTO> documents;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> fileUrls = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"exercises"})
    private Course course;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}