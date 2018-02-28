package restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import restaurant.util.UtilId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Restaurants r WHERE r.id=:id"),
        @NamedQuery(name = Menu.ALL_Restaurants, query = "SELECT m FROM Menu m WHERE m.Restaurants.id=:restauranId " +
                "AND m.meals.id=:mealId ORDER BY m.Restaurants.name"),
        @NamedQuery(name = Menu.DELETE, query = "SELECT Restaurants.name, Meals.meal,Meals.price,Meals.id, Meals.Restaurants.id FROM Restaurants LEFT JOIN Meals ON Restaurants.id = Meals.Restaurants.id"),
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
    private Restaurants Restaurants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Meals meals;

    public Menu() {
    }

    public Menu(LocalDateTime dateTime, Restaurants restaurants) {
        this.dateTime = dateTime;
        this.Restaurants = restaurants;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public restaurant.model.Restaurants getRestaurants() {
        return Restaurants;
    }
    public void setRestaurants(restaurant.model.Restaurants restaurants) {
        Restaurants = restaurants;
    }
    public Meals getMeals() {
        return meals;
    }
    public void setMeals(Meals meals) {
        this.meals = meals;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
