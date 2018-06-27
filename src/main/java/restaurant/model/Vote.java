package restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.getAllSorted, query = "SELECT v FROM Vote v ORDER BY v.date_time"),
        @NamedQuery(name = Vote.getDate, query = "SELECT v FROM Vote v WHERE cast(date_time as date) = cast(:sdate as date) AND user_id=:user_id"),
})
@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    public static final String DELETE = "Vote.delete";
    public static final String getAllSorted = "Vote.getAllSorted";
    public static final String ratingVote = "Vote.ratingVote";
    public static final String getDate = "Vote.getDate";

    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    private LocalDateTime date_time;
    @Column(name = "vote", nullable = false, unique = true)
    private Integer vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restauran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference
    private Restaurants restaurants;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Vote() {
    }
    public Vote(Integer id,LocalDateTime date_time, Integer vote) {
        this.id=id;
        this.date_time = date_time;
        this.vote = vote;
    }

    public Vote(LocalDateTime date_time, Integer vote) {
        this.date_time = date_time;
        this.vote = vote;
    }

    public Vote(Integer vote, @NotNull User user) {
        this.vote = vote;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getDate_time() {
        return date_time;
    }
    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }
    public Integer getVote() {
        return vote;
    }
    public void setVote(Integer voite) {
        this.vote = voite;
    }

    public Restaurants getRestaurants() {
        return restaurants;
    }
    public void setRestaurants(restaurant.model.Restaurants restaurants) {
        this.restaurants = restaurants;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date_time=" + date_time +
                ", vote=" + vote +
                '}';
    }
}
