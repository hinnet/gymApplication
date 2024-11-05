package hh.sof03.harjoitustyo.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PerformanceRepository extends CrudRepository<Performance, Long> {
    List<Performance> findByWorkout(Workout workout);
}