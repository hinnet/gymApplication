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
    private String exercise;
    private double weights;
    private int sets;
    private int reps;
    private int rest;

    @ManyToOne
    @JsonIgnoreProperties("performances")
    @JoinColumn(name = "workoutid")
    private Workout workout;

    public Performance() {
    }

    public Performance(String exercise, double weights, int sets, int reps, int rest, Workout workout) {
        super();
        this.exercise = exercise;
        this.weights = weights;
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
        this.workout = workout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getweights() {
        return weights;
    }

    public void set(double weights) {
        this.weights = weights;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public Workout getWorkout() {
        return workout;
    }
    
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    @Override
    public String toString() {
        if (this.workout != null)
            return "Performance [id=" + id + ", weights=" + weights + ", sets=" + sets + ", reps=" + reps + ", workout =" + this.getWorkout() + "]";
        else
            return "Performance [id=" + id + ", weights=" + weights + ", sets=" + sets + ", reps=" + reps + "]";
    }
}
