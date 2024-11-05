package hh.sof03.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.sof03.harjoitustyo.domain.Exercise;
import hh.sof03.harjoitustyo.domain.ExerciseRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExerciseController {

    @Autowired
    private ExerciseRepository repository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/exerciselist")
    public String exerciseList(Model model) {
        model.addAttribute("exercises", repository.findAll());
        return "exerciselist";
    }

    // Add new exercise, returns new-exercise form
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new-exercise")
    public String addExercise(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "new-exercise";
    }

    // Save new exercise to database
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save-exercise")
    public String saveExercise(Exercise exercise) {
        repository.save(exercise);
        return "redirect:/exerciselist";
    }

    // Edit exercise
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit-exercise/{id}")
    public String editExercise(@PathVariable("id") Long exerciseId, Model model) {
        model.addAttribute("exercise", repository.findById(exerciseId));
        return "edit-exercise";
    }

    // Update exercise
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-exercise")
    public String updateExercise(Exercise exercise) {
        repository.save(exercise);
        return "redirect:/exerciselist";
    }

    // Delete exercise from database
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete-exercise/{id}")
    public String deleteExercise(@PathVariable("id") Long exerciseId, Model model) {
    repository.deleteById(exerciseId);
        return "redirect:/exerciselist";
    }
}
