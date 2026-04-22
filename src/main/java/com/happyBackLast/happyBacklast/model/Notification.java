package com.happyBackLast.happyBacklast.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    private String title;
    private String message;
    private String targetLevel;
    private String targetFiliere;
    private String date;

}