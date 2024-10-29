package hh.sof03.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof03.harjoitustyo.domain.Performance;
import hh.sof03.harjoitustyo.domain.PerformanceRepository;

@Controller
public class PerformanceController {
    @Autowired
    private PerformanceRepository repository;

    // Edit performance
    @GetMapping("/edit-performance/{id}")
    public String editPerformance(@PathVariable("id") Long performanceId, Model model) {
        model.addAttribute("performance", repository.findById(performanceId));
        return "edit-performance";
    }

    // Update performance
    @PostMapping("/update-performance")
    public String updatePerformance(Performance performance) {
        repository.save(performance);
        return "redirect:/workoutlist";
    }

    // Delete performance from workout (and database)
    @GetMapping("/delete-performance/{id}")
    public String deletePerformance(@PathVariable("id") Long performanceId, Model model) {
        repository.deleteById(performanceId);
        return "redirect:/workoutlist";
    }

}
