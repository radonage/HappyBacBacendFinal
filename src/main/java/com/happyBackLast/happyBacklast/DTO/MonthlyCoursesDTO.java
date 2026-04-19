package com.happyBackLast.happyBacklast.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyCoursesDTO {
    private String month;
    private long total;
}