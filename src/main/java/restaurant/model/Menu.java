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
     //   @NamedQuery(name = Menu.ALL_Restaurants, query = "SELECT m FROM Menu m WHERE m.restaurants.id=:restauranId " +
     //           " ORDER BY m.restaurants.name"),
     //   @NamedQuery(name = Menu.getAllSorted, query = "SELECT Restaurants.name, Meals.meal,Meals.price,Meals.id, Meals.restaurants.id FROM Restaurants " +
     //           "LEFT JOIN Meals ON Restaurants.id = Meals.restaurants.id"),
})
@Entity
@Table(name = "menu")
public class Menu implements UtilId {
    public static final int START_SEQ = 100000;

    public static final String DELETE = "Menu.delete";
    public static final String ALL_Restaurants = "Menu.getAllSorted";

    @Id
    @Column(name="id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "date", columnDefinition = "timestamp default now()")
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restauran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Set<Restaurants> restaurants;

    public Menu() {
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Set<Restaurants> getRestaurants() {
        return restaurants;
    }
    public void setRestaurants(Set<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }
}
