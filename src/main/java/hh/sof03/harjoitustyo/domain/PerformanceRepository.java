package hh.sof03.harjoitustyo.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepository extends CrudRepository<Performance, Long> {
    List<Performance> findByExercise(String exercise);
}
