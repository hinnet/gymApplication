package hh.sof03.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.sof03.harjoitustyo.domain.Workout;
import hh.sof03.harjoitustyo.domain.WorkoutRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WorkoutController {
    @Autowired
    private WorkoutRepository repository;

    // Request list of all workouts and return html to web browser
    @GetMapping("/workoutlist")
    public String workoutList(Model model) {
        model.addAttribute("workouts", repository.findAll());
        return "workoutlist";
    }

    // Add new workout, return new-workout form
    @GetMapping("/new-workout")
    public String addWorkout(Model model) {
        model.addAttribute("workout", new Workout());
        return "new-workout";
    }
    
    @PostMapping("/save-workout")
    public String saveWorkout(Workout workout) {
        repository.save(workout);
        return "redirect:/workoutlist";
    }

    @GetMapping("/delete-workout/{id}")
    public String deleteWorkout(@PathVariable("id") Long workoutId, Model model) {
        repository.deleteById(workoutId);
        return "redirect:/workoutlist";
    }

    @GetMapping("/edit-workout/{id}")
    public String editWorkout(@PathVariable("id") Long workoutId, Model model) {
        model.addAttribute("workout", repository.findById(workoutId));
        return "edit-workout";
    }
}
