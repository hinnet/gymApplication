package hh.sof03.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.sof03.harjoitustyo.domain.Performance;
import hh.sof03.harjoitustyo.domain.PerformanceRepository;
import hh.sof03.harjoitustyo.domain.Workout;
import hh.sof03.harjoitustyo.domain.WorkoutRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WorkoutController {
    @Autowired
    private WorkoutRepository woRepository;

    @Autowired
    private PerformanceRepository perfRepository;

    // Request list of all workouts and return html to web browser
    @GetMapping("/workoutlist")
    public String workoutList(Model model) {
        model.addAttribute("workouts", woRepository.findAll());
        return "workoutlist";
    }

    // Add new workout, returns new-workout form
    @GetMapping("/new-workout")
    public String addWorkout(Model model) {
        model.addAttribute("workout", new Workout());
        model.addAttribute("performance", new Performance());
        return "new-workout";
    }
    
    // Save new workout to database
    @PostMapping("/save-workout")
    public String saveWorkout(Workout workout, Performance performance) {
        woRepository.save(workout);
        perfRepository.save(performance);
        return "redirect:/workoutlist";
    }

    // Edit workout
    @GetMapping("/edit-workout/{id}")
    public String editWorkout(@PathVariable("id") Long workoutId, Model model) {
        model.addAttribute("workout", woRepository.findById(workoutId));
        model.addAttribute("performances", woRepository.findById(workoutId));
        return "edit-workout";
    }

    // Update workout
    @PostMapping("/update-workout")
    public String updateWorkout(Workout workout) {
        woRepository.save(workout);
        return "redirect:/workoutlist";
    }

    // Delete workout from database
    @GetMapping("/delete-workout/{id}")
    public String deleteWorkout(@PathVariable("id") Long workoutId, Model model) {
    woRepository.deleteById(workoutId);
        return "redirect:/workoutlist";
    }
}
