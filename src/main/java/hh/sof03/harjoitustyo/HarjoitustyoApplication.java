package hh.sof03.harjoitustyo;

import java.time.LocalDate;
import java.time.LocalTime;
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
import hh.sof03.harjoitustyo.domain.AppUser;
import hh.sof03.harjoitustyo.domain.AppUserRepository;
import hh.sof03.harjoitustyo.domain.Exercise;
import hh.sof03.harjoitustyo.domain.ExerciseRepository;
import hh.sof03.harjoitustyo.domain.Workout;
import hh.sof03.harjoitustyo.domain.WorkoutRepository;

@SpringBootApplication
public class HarjoitustyoApplication {
	private static final Logger log = LoggerFactory.getLogger(HarjoitustyoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AppUserRepository uRepository, ExerciseRepository eRepository, WorkoutRepository wRepository, PerformanceRepository pRepository) {
		return (args) -> {

			// user1/user1 admin/admin
			AppUser user1 = new AppUser("user1", "$2a$12$9akGgVN01Th0dXNX5rLSBOGcjklMm2hAojqz3nX6CbnbxPlCgX.BW", "USER");
			AppUser user2 = new AppUser("admin", "$2a$12$xSKSL4wxAVITacS3mKlbiuEarX9XqVEZ.yGWidYtvjE/.AjAapSYi", "ADMIN");

			uRepository.save(user1);
			uRepository.save(user2);

			uRepository.findAll().forEach(user -> {
				log.info(user.toString());
			});

			Exercise exercise1 = new Exercise("Bench Press");
			Exercise exercise2 = new Exercise("Overhead Press");
			Exercise exercise3 = new Exercise("Lateral Raise");
			Exercise exercise4 = new Exercise("Cable Crunch");
			Exercise exercise5 = new Exercise("Squat");
			Exercise exercise6 = new Exercise("Romanian Deadlift");
			Exercise exercise7 = new Exercise("Leg Extension");
			Exercise exercise8 = new Exercise("Seated Calf Raise");
			Exercise exercise9 = new Exercise("Deadlift");
			Exercise exercise10 = new Exercise("Lat Pulldown");
			Exercise exercise11 = new Exercise("Bicep Curl");
			Exercise exercise12 = new Exercise("Bent Over One Arm Row");
			Exercise exercise13 = new Exercise("Hip Thrust");
			Exercise exercise14 = new Exercise("Bulgarian Split Squat");
			Exercise exercise15 = new Exercise("Seated Leg Curl");
			Exercise exercise16 = new Exercise("Standing Calf Raises");

			List<Exercise> exercises = Arrays.asList(
				exercise1, exercise2, exercise3, exercise4, exercise5, exercise6, exercise7, exercise8, exercise9,
				exercise10, exercise11, exercise12, exercise13, exercise14, exercise15, exercise16
			);
			eRepository.saveAll(exercises);

			eRepository.findAll().forEach(exercise -> {
				log.info(exercise.toString());
			});

			Workout workout1 = new Workout("Upper - Workout 1", LocalDate.of(2024, 11, 7), LocalTime.of(9, 10), LocalTime.of(10, 30), user1);
			Workout workout2 = new Workout("Lower - Workout 1", LocalDate.of(2024, 11, 7), LocalTime.of(11, 55), LocalTime.of(13, 15), user1);
			Workout workout3 = new Workout("Upper - Workout 2", LocalDate.of(2024, 11, 7), LocalTime.of(18, 00), LocalTime.of(19, 20), user1);
			Workout workout4 = new Workout("Lower - Workout 2", LocalDate.of(2024, 11, 7), LocalTime.of(6, 30), LocalTime.of(7, 30), user1);

			wRepository.save(workout1);
			wRepository.save(workout2);
			wRepository.save(workout3);
			wRepository.save(workout4);

			wRepository.findAll().forEach(workout -> {
				log.info(workout.toString());
			});

			Performance performance1 = new Performance(80.5, 4, 5, 180, exercise1, workout1);
			Performance performance2 = new Performance(50.0, 3, 5, 120, exercise2, workout1);
			Performance performance3 = new Performance(20, 3, 10, 80, exercise3, workout1);
			Performance performance4 = new Performance(100, 3, 10, 80, exercise4, workout1);

			Performance performance5 = new Performance(102.5, 3, 5, 180, exercise5, workout2);
			Performance performance6 = new Performance(80.5, 3, 8, 90, exercise6, workout2);
			Performance performance7 = new Performance(80, 3, 8, 90, exercise7, workout2);
			Performance performance8 = new Performance(65, 4, 10, 70, exercise8, workout2);

			Performance performance9 = new Performance(100, 3, 5, 180, exercise9, workout3);
			Performance performance10 = new Performance(80, 3, 8, 90, exercise10, workout3);
			Performance performance11 = new Performance(42.5, 3, 12, 90, exercise11, workout3);
			Performance performance12 = new Performance(50, 3, 8, 90, exercise12, workout3);

			Performance performance13 = new Performance(160, 4, 6, 180, exercise13, workout4);
			Performance performance14 = new Performance(45, 3, 8, 90, exercise14, workout4);
			Performance performance15 = new Performance(80, 3, 10, 90, exercise15, workout4);
			Performance performance16 = new Performance(45, 3, 12, 80, exercise16, workout4);
			
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
