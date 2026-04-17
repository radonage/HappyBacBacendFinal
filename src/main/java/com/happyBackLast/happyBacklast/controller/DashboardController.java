package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.dto.*;
import com.happyBackLast.happyBacklast.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final CourseRepository courseRepo;
    private final SubjectRepository subjectRepo;
    private final LevelRepository levelRepo;
    private final FiliereRepository filiereRepo;

    public DashboardController(
            CourseRepository courseRepo,
            SubjectRepository subjectRepo,
            LevelRepository levelRepo,
            FiliereRepository filiereRepo
    ) {
        this.courseRepo = courseRepo;
        this.subjectRepo = subjectRepo;
        this.levelRepo = levelRepo;
        this.filiereRepo = filiereRepo;
    }

    @GetMapping("/stats")
    public DashboardStatsDTO getStats(@RequestParam Long countryId) {
        return new DashboardStatsDTO(
                courseRepo.countByCountry_Id(countryId),
                subjectRepo.countByCountry_Id(countryId),
                levelRepo.countByCountry_Id(countryId),
                filiereRepo.countByCountry_Id(countryId)
        );
    }

    @GetMapping("/courses-growth")
    public List<MonthlyCoursesDTO> getCourseGrowth(@RequestParam Long countryId) {
        return courseRepo.countCoursesByMonth(countryId)
                .stream()
                .map(obj -> new MonthlyCoursesDTO(
                        (String) obj[0],
                        ((Number) obj[1]).longValue()
                ))
                .toList();
    }

    @GetMapping("/courses-by-subject")
    public List<SubjectStatsDTO> getBySubject(@RequestParam Long countryId) {
        return subjectRepo.countCoursesBySubject(countryId)
                .stream()
                .map(obj -> new SubjectStatsDTO(
                        (String) obj[0],
                        ((Number) obj[1]).longValue()
                ))
                .toList();
    }
}