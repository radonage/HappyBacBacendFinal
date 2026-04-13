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
    public DashboardStatsDTO getStats() {
        return new DashboardStatsDTO(
                courseRepo.countCourses(),
                subjectRepo.countSubjects(),
                levelRepo.countLevels(),
                filiereRepo.countFilieres()
        );
    }

    @GetMapping("/courses-growth")
    public List<MonthlyCoursesDTO> getCourseGrowth() {
        return courseRepo.countCoursesByMonth()
                .stream()
                .map(obj -> new MonthlyCoursesDTO(
                        (String) obj[0],
                        (Long) obj[1]
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/courses-by-subject")
    public List<SubjectStatsDTO> getBySubject() {
        return subjectRepo.countCoursesBySubject()
                .stream()
                .map(obj -> new SubjectStatsDTO(
                        (String) obj[0],
                        (Long) obj[1]
                ))
                .collect(Collectors.toList());
    }
}