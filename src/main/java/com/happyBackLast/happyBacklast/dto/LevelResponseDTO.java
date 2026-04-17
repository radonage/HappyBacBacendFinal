package com.happyBackLast.happyBacklast.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelResponseDTO {
    private Long id;
    private String name;
    private Long filiereId;
    private String filiereName;
    private Long countryId;
    private String countryName;
}