package hh.sof03.harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import hh.sof03.harjoitustyo.domain.Exercise;
import hh.sof03.harjoitustyo.domain.ExerciseRepository;
import hh.sof03.harjoitustyo.domain.Performance;
import hh.sof03.harjoitustyo.domain.PerformanceRepository;
import hh.sof03.harjoitustyo.domain.Workout;
import hh.sof03.harjoitustyo.domain.WorkoutRepository;

@Controller
public class PerformanceController {

    @Autowired
    private PerformanceRepository repository;

    @Autowired
    private ExerciseRepository eRepository;

    @Autowired
    private WorkoutRepository woRepository;

    // RESTful service to get all performances
    // Get Java-list of Performance objects, convert to JSON-list and send to web browser
    @GetMapping("/performances")
    public List<Performance> performanceListRest() {
        return (List<Performance>) repository.findAll();
    }

    // RESTful service to get performance by id
    @GetMapping("/performances/{id}")
    public Optional<Performance> findPerformanceRest(@PathVariable("id") Long performanceId) {
        return repository.findById(performanceId);
    }

    // RESTful service to save new performance
    @PostMapping("/performances")
    public Performance savePerformanceRest(@RequestBody Performance performance) {
        return repository.save(performance);
    }

    // Save new performance to workout
    @PostMapping("/add-performance")
    public String addNewPerformanceToWorkout(@RequestParam("workoutId") Long workoutId, @RequestParam("exerciseId") Long exerciseId, Performance performance) {
        Workout workout = woRepository.findById(workoutId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid workout ID: " + workoutId));
        performance.setWorkout(workout);

        Exercise exercise = eRepository.findById(exerciseId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid exercise ID: " + exerciseId));
        performance.setExercise(exercise);
        
        repository.save(performance);
        return "redirect:/edit-workout/" + performance.getWorkout().getId();
    }
    
    // Edit performance
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/edit-performance/{id}")
    public String editPerformance(@PathVariable("id") Long performanceId, Model model) {
        model.addAttribute("performance", repository.findById(performanceId));
        model.addAttribute("exercises", eRepository.findAll());
        return "edit-performance";
    }

    // Update performance
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/update-performance")
    public String updatePerformance(Performance performance) {
        repository.save(performance);
        return "redirect:/edit-workout/" + performance.getWorkout().getId();
    }

    // Delete performance from workout (and database)
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/delete-performance/{id}")
    public String deletePerformance(@PathVariable("id") Long performanceId, Model model) {
        Performance performance = repository.findById(performanceId).get();
        Workout workout = performance.getWorkout();
        repository.deleteById(performanceId);
        return "redirect:/edit-workout/" + workout.getId();
    }

}
