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

// import java.util.Date;
import java.util.List;
// import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    //private Date date;
    //TODO: Lisää date getterit, setterit...
    // TODO: kesto?
    // TODO: kokonaispainot (workout load)?

    @ManyToOne
    @JsonIgnoreProperties("workouts")
    @JoinColumn(name = "userid")
    private AppUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workout")
    @JsonIgnoreProperties("workout")
    private List<Performance> performances;

    public Workout() {
    }

    public Workout(String title, AppUser user, List<Performance> performances) {
        super();
        this.title = title;
        this.user = user;
        this.performances = performances;
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Workout [id=" + id + ", title=" + title + ", user=" + user + ", performances=" + performances + "]";
    }
}
