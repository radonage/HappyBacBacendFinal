package com.happyBackLast.happyBacklast.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.happyBackLast.happyBacklast.dto.DocumentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties({"exams", "courses", "level"})
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonIgnoreProperties({"subjects"})
    private Level level;

    private String title;
    private String type;

    @Column(name = "exam_year")
    private String year;
    private String description;
    private String correctionVideoUrl;

    @Transient
    private List<DocumentDTO> documents;

    @ElementCollection
    @CollectionTable(name = "exam_files", joinColumns = @JoinColumn(name = "exam_id"))
    @Column(name = "file_url")
    private List<String> fileUrls;

}