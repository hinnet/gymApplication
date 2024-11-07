package hh.sof03.harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.sof03.harjoitustyo.domain.ExerciseRepository;
import hh.sof03.harjoitustyo.domain.Performance;
import hh.sof03.harjoitustyo.domain.PerformanceRepository;
import hh.sof03.harjoitustyo.domain.Workout;
import hh.sof03.harjoitustyo.domain.WorkoutRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WorkoutController {
    @Autowired
    private WorkoutRepository woRepository;

    @Autowired
    private PerformanceRepository perfRepository;

    @Autowired
    private ExerciseRepository eRepository;

    // Home page
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    
    // Request list of all workouts and return html to web browser
    @GetMapping("/workoutlist")
    public String workoutList(Model model) {
        model.addAttribute("workouts", woRepository.findAll());
        return "workoutlist";
    }

    // RESTful service to get all workouts
    @GetMapping("/workouts")
    public List<Workout> getWorkoutsRest() {
        return (List<Workout>) woRepository.findAll();
    }

    // RESTful service to get workout by id
    @RequestMapping(value="/workout/{id}")
    public @ResponseBody Optional<Workout> findWorkoutRest(@PathVariable("id") Long workoutId) {	
    	return woRepository.findById(workoutId);
    }       
    
    // Add new workout, returns new-workout form
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/new-workout")
    public String addWorkout(Model model) {
        model.addAttribute("workout", new Workout());
        model.addAttribute("performance", new Performance());
        return "new-workout";
    }
    
    // Save new workout to database
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/save-workout")
    public String saveWorkout(Workout workout, Performance performance) {
        woRepository.save(workout);
        perfRepository.save(performance);
        return "redirect:/workoutlist";
    }

    // Edit workout
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/edit-workout/{id}")
    public String editWorkout(@PathVariable("id") Long workoutId, Model model) {
        Workout workout = woRepository.findById(workoutId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid workout ID: " + workoutId));
        model.addAttribute("workout", workout);
        model.addAttribute("performances", perfRepository.findByWorkout(workout));
        model.addAttribute("performance", new Performance());
        model.addAttribute("exercises", eRepository.findAll());
        return "edit-workout";
    }

    // Update workout
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/update-workout")
    public String updateWorkout(Workout workout, RedirectAttributes redirectAttributes) {
        woRepository.save(workout);
        redirectAttributes.addFlashAttribute("message", "Workout Title saved successfully!");
        return "redirect:/edit-workout/" + workout.getId();
    }

    // Delete workout from database
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/delete-workout/{id}")
    public String deleteWorkout(@PathVariable("id") Long workoutId, Model model) {
    woRepository.deleteById(workoutId);
        return "redirect:/workoutlist";
    }
}
