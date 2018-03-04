package restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import restaurant.util.UtilId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Restaurants r WHERE r.id=:id"),
        @NamedQuery(name = Menu.ALL_Restaurants, query = "SELECT m FROM Menu m WHERE m.restaurants.id=:restauranId " +
                " ORDER BY m.restaurants.name"),
        @NamedQuery(name = Menu.DELETE, query = "SELECT Restaurants.name, Meals.meal,Meals.price,Meals.id, Meals.restaurants.id FROM Restaurants " +
                "LEFT JOIN Meals ON Restaurants.id = Meals.restaurants.id"),
})
@Entity
@Table(name = "menu")
public class Menu implements UtilId {
    public static final int START_SEQ = 100000;

    public static final String DELETE = "Restaurants.delete";
    public static final String ALL_Restaurants = "Restaurants.getAllSorted";

    @Id
    @Column(name="id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restauran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurants restaurants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Vote vote;

    public Menu() {
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurants getRestaurants() {
        return restaurants;
    }
    public void setRestaurants(Restaurants restaurants) {
        this.restaurants = restaurants;
    }
    public Vote getVote() {
        return vote;
    }
    public void setVote(Set<Vote> voite) {
        this.vote = vote;
    }
}
