package hh.sof03.harjoitustyo.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {
    List<Workout> findByTitle(String title);
}
