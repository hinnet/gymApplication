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
    private int sets;
    private int reps;

    @ManyToOne
    @JsonIgnoreProperties ("performances")
    @JoinColumn(name = "workoutid")
    private Workout workout;

    public Performance() {
    }

    public Performance(double weights, int sets, int reps, Workout workout) {
        super();
        this.weights = weights;
        this.sets = sets;
        this.reps = reps;
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
