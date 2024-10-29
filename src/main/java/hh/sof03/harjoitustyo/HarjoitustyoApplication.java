package hh.sof03.harjoitustyo;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.harjoitustyo.domain.Performance;
import hh.sof03.harjoitustyo.domain.PerformanceRepository;
import hh.sof03.harjoitustyo.domain.User;
import hh.sof03.harjoitustyo.domain.UserRepository;
import hh.sof03.harjoitustyo.domain.Workout;
import hh.sof03.harjoitustyo.domain.WorkoutRepository;

@SpringBootApplication
public class HarjoitustyoApplication {
	private static final Logger log = LoggerFactory.getLogger(HarjoitustyoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository uRepository, WorkoutRepository wRepository, PerformanceRepository pRepository) {
		return (args) -> {
			User user = new User("testUser", "null", null);

			uRepository.save(user);

			Workout workout1 = new Workout("Upper - Workout 1", user, null);
			Workout workout2 = new Workout("Lower - Workout 1", user, null);
			Workout workout3 = new Workout("Upper - Workout 2", user, null);
			Workout workout4 = new Workout("Lower - Workout 2", user, null);

			wRepository.save(workout1);
			wRepository.save(workout2);
			wRepository.save(workout3);
			wRepository.save(workout4);

			wRepository.findAll().forEach(workout -> {
				log.info(workout.toString());
			});

			Performance performance1 = new Performance("Bench Press", 80.5, 4, 5, 180, workout1);
			Performance performance2 = new Performance("Overhead Press", 50.0, 3, 5, 120, workout1);
			Performance performance3 = new Performance("Lateral Raise", 20, 3, 10, 80, workout1);
			Performance performance4 = new Performance("Cable Crunch", 100, 3, 10, 80, workout1);

			Performance performance5 = new Performance("Squat", 102.5, 3, 5, 180, workout2);
			Performance performance6 = new Performance("Romanian Deadlift", 80.5, 3, 8, 90, workout2);
			Performance performance7 = new Performance("Leg Extension", 80, 3, 8, 90, workout2);
			Performance performance8 = new Performance("Seated Calf Raise", 65, 4, 10, 70, workout2);

			Performance performance9 = new Performance("Deadlift", 100, 3, 5, 180, workout3);
			Performance performance10 = new Performance("Lat Pulldown", 80, 3, 8, 90, workout3);
			Performance performance11 = new Performance("Bicep Curl", 42.5, 3, 12, 90, workout3);
			Performance performance12 = new Performance("Bent Over One Arm Row", 50, 3, 8, 90, workout3);

			Performance performance13 = new Performance("Hip Thrust", 160, 4, 6, 180, workout4);
			Performance performance14 = new Performance("Bulgarian Split Squat", 45, 3, 8, 90, workout4);
			Performance performance15 = new Performance("Seated Leg Curl", 80, 3, 10, 90, workout4);
			Performance performance16 = new Performance("Standing Calf Raises", 45, 3, 12, 80, workout4);
			
			List<Performance> performances = Arrays.asList(
				performance1, performance2, performance3, performance4, performance5, performance6, performance7, performance8, performance9,
				performance10, performance11, performance12, performance13, performance14, performance15, performance16
			);
			pRepository.saveAll(performances);

			pRepository.findAll().forEach(performance -> {
				log.info(performance.toString());
			});
		};
	}

}
