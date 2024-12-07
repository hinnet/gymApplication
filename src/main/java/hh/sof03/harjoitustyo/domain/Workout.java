package hh.sof03.harjoitustyo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    // 'T' erottaa pvm ja kloajan, jotta datetime-local saadaan toimimaan oikein Thymeleafissa
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    //private String duration;

    @ManyToOne
    @JsonIgnoreProperties("workouts")
    @JoinColumn(name = "userid")
    private AppUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workout")
    @JsonIgnoreProperties("workout")
    private List<Performance> performances;

    public Workout() {
    }

    public Workout(String title, LocalDateTime startTime, LocalDateTime endTime, AppUser user) {
        super();
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        if (startTime == null || endTime == null) {
            return "";
        }

        Duration duration = Duration.between(startTime, endTime);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        // Palautetaan kesto muodossa hh:mm (%d = korvattava paikkamerkki, 02 = varmistaa, että numero on kaksilukuinen esim. 00, 11, 05 jne)
        return String.format("%02d:%02d" + " Hours", hours, minutes);
    }

    // public void setDuration(String duration) {
    //     this.duration = duration;
    // }
    
    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
