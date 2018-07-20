package restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = Meals.DELETE, query = "DELETE FROM Meals m WHERE m.id=:id"),
        @NamedQuery(name = Meals.ALL_SORTED, query = "SELECT m FROM Meals m ORDER BY m.meal"),
        @NamedQuery(name = Meals.ALL_SORTEDBYID, query = "SELECT m FROM Meals m WHERE m.restaurants.id=:restaurantId ORDER BY m.meal"),
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = "restauran_id", name = "meals_unique_restaurant_idx")})
public class Meals extends AbstractBaseEntity implements Serializable {

    public static final String DELETE = "Meals.delete";
    public static final String ALL_SORTED = "Meals.getAllSorted";
    public static final String ALL_SORTEDBYID = "Meals.getAllSortedById";

    @Column(name = "meal", nullable = false)
    //@NotBlank
    @NotNull
    @Size(min = 2, max = 32)
    private String meal;

    @Column(name = "price", nullable = false)
    //@NotBlank
    @NotNull
    // @Size(min = 2, max = 100)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restauran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @NotNull
    @JsonBackReference
    private Restaurants restaurants;

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

    public Meals(Meals meals) {
      this(meals.getId(), meals.getMeal(), meals.getPrice());
    }

    public Meals(String meal, Restaurants restaurants, int price) {
        this.meal = meal;
        this.price = price;
        this.restaurants = restaurants;
    }
    public Meals(int id,String meal, Restaurants restaurants, int price) {
        this.id=id;
        this.meal = meal;
        this.price = price;
        this.restaurants = restaurants;
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
        return this.restaurants;
    }

    public void setRestaurants(Restaurants restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "Meals{" +
                "id=" + id +
                ", meal='" + meal + '\'' +
                ", price=" + price +
                '}';
    }
}
