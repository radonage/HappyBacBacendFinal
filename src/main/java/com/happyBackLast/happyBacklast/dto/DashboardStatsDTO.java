package com.happyBackLast.happyBacklast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStatsDTO {
    private long totalCourses;
    private long totalSubjects;
    private long totalLevels;
    private long totalFilieres;
}