package com.happyBackLast.happyBacklast.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.happyBackLast.happyBacklast.DTO.DocumentDTO;
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

    @Transient
    private List<DocumentDTO> documents;

    @ElementCollection
    @CollectionTable(
            name = "course_file_urls",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @Column(name = "file_url")
    private List<String> fileUrls;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties({"courses", "level"})
    private Subject subject;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("course")
    private List<Exercise> exercises;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}