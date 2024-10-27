package hh.sof03.harjoitustyo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hh.sof03.harjoitustyo.domain.Workout;
import hh.sof03.harjoitustyo.domain.WorkoutRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class WorkoutRestController {
    @Autowired
    private WorkoutRepository repository;

    // RESTful service to get all workouts
    @GetMapping("/workouts")
    public List<Workout> getWorkoutsRest() {
        return (List<Workout>) repository.findAll();
    }
}
