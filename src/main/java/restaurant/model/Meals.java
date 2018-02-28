package restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import restaurant.util.UtilId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Meals.DELETE, query = "DELETE FROM Meals m WHERE m.id=:id AND m.Restaurants.id=:restaurantId"),
        @NamedQuery(name = Meals.ALL_SORTED, query = "SELECT m FROM Meals m ORDER BY m.meal"),
        @NamedQuery(name = Meals.ALL_SORTEDBYID, query = "SELECT m FROM Meals m WHERE m.Restaurants.id=:restaurantId ORDER BY m.meal"),
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = "restauran_id", name = "meals_unique_restaurant_idx")})
public class Meals implements UtilId {
    public static final int START_SEQ = 100000;

    public static final String DELETE = "Meals.delete";
    public static final String ALL_SORTED = "Meals.getAllSorted";
    public static final String ALL_SORTEDBYID = "Meals.getAllSortedById";

    @Id
    @Column(name="id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;
    @Column(name = "meal", nullable = false)
    private String meal;
   // @Column(name = "date_time", columnDefinition = "timestamp default now()")
   // private LocalDateTime dateTime;
    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "restauran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurants Restaurants;

    public Meals() {
    }

    public Meals(Integer id, String meal, int price) {
        this.id = id;
        this.meal = meal;
        this.price = price;
    }

    public Meals(String meal, int price) {
        this.meal = meal;
        this.price = price;
    }

    public Meals(String meal, Restaurants restaurants, int price) {
        this.meal = meal;
        this.price = price;
        this.Restaurants = restaurants;
    }
    public Meals(int id,String meal, Restaurants restaurants, int price) {
        this.id=id;
        this.meal = meal;
        this.price = price;
        this.Restaurants = restaurants;
    }
    public Meals(int id,String meal, int price) {
        this.id=id;
        this.meal = meal;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMeal() {
        return meal;
    }
    public void setMeal(String meal) {
        this.meal = meal;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Restaurants getRestaurants() {
        return Restaurants;
    }
    public void setRestaurants(Restaurants restaurants) {
        this.Restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "Meals{" +
                "id=" + id +
                ", meal='" + meal + '\'' +
                ", price=" + price +
                '}';
    }

    public boolean isNew() {
        return this.id == null;
    }
}
