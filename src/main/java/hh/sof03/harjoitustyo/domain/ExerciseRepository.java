package hh.sof03.harjoitustyo.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    List<Exercise> findByName(String name);
}
