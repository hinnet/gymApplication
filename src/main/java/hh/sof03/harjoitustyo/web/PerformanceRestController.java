package hh.sof03.harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hh.sof03.harjoitustyo.domain.Performance;
import hh.sof03.harjoitustyo.domain.PerformanceRepository;

@Controller
public class PerformanceRestController {
    @Autowired
    private PerformanceRepository repository;

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
}
