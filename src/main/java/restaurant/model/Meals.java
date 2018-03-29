package restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import restaurant.util.UtilId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Meals.DELETE, query = "DELETE FROM Meals m WHERE m.id=:id AND m.restaurants.id=:restaurantId"),
        @NamedQuery(name = Meals.ALL_SORTED, query = "SELECT m FROM Meals m ORDER BY m.meal"),
        @NamedQuery(name = Meals.ALL_SORTEDBYID, query = "SELECT m FROM Meals m WHERE m.restaurants.id=:restaurantId ORDER BY m.meal"),
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = "restauran_id", name = "meals_unique_restaurant_idx")})
public class Meals implements UtilId {
    public static final int START_SEQ = 100000;

    public static final String DELETE = "Meals.delete";
    public static final String ALL_SORTED = "Meals.getAllSorted";
    public static final String ALL_SORTEDBYID = "Meals.getAllSortedById";

    @Id
    @Column(name="id", unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;
    @Column(name = "meal", nullable = false)
    private String meal;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restauran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference
    private Restaurants restaurants;

    public Meals() {
    }

    public Meals(Integer id, String meal, Integer price) {
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
/*
    public Meals(int id,String meal, Integer restaurants, int price) {
        this.id=id;
        this.meal = meal;
        this.price = price;
        this.restaurants=restaurants
       // this.restaurants = setRestaurants(restaurants);
    }
    */

    public Meals(Integer id,String meal, int price) {
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
        return this.restaurants;
    }
/*
    public Restaurants getIdFromName(String name) {
        return setRestaurants(Integer.valueOf(name));
    }
    */
    public void setRestaurants(Restaurants restaurants) {
        /*
        if (restaurants.getName()!=null && restaurants.getId()==null){
            this.restaurants=getIdFromName(restaurants.getName());
        }else
            */
        this.restaurants = restaurants;
    }
/*
    public Restaurants setRestaurants(Integer restaurants) {
        return new Restaurants(restaurants);
    }
    */

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
