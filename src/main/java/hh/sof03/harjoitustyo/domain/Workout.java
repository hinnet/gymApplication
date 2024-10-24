package hh.sof03.harjoitustyo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    // TODO: kesto? aloitus ja lopetus
    // TODO: kokonaispainot (workout load)?

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workout")
    @JsonIgnoreProperties("workout")  
    private List<Performance> performances;
    
    public Workout() {
    }

    public Workout(String title, User user) {
        super();
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    @Override
    public String toString() {
        return "Workout [id=" + id + ", title=" + title + "]";
    }

}
