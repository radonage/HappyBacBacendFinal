package com.happyBackLast.happyBacklast.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDTO {
    private long totalCourses;
    private long totalSubjects;
    private long totalLevels;
    private long totalFilieres;
}