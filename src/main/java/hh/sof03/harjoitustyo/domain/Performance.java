package hh.sof03.harjoitustyo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Performance {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private double weights;
    private Integer sets;
    private Integer reps;
    private Integer rest;

    @ManyToOne
    @JsonIgnoreProperties("performances")
    @JoinColumn(name = "exerciseid")
    private Exercise exercise;

    @ManyToOne
    @JsonIgnoreProperties("performances")
    @JoinColumn(name = "workoutid")
    private Workout workout;

    public Performance() {
    }

    public Performance(double weights, Integer sets, Integer reps, Integer rest, Exercise exercise, Workout workout) {
        super();
        this.weights = weights;
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
        this.exercise = exercise;
        this.workout = workout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeights() {
        return weights;
    }

    public void setWeights(double weights) {
        this.weights = weights;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getRest() {
        return rest;
    }

    public void setRest(Integer rest) {
        this.rest = rest;
    }
    
    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
