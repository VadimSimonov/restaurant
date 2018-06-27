package restaurant.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.getAllSorted, query = "SELECT m FROM Menu m"),
})
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String getAllSorted = "Menu.getAllSorted";

    @Column(name = "date", columnDefinition = "timestamp default now()")
    private LocalDate date;


   @OneToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "menu_day", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "restauran_id"))
    private Set<Restaurants> restaurants;

    public Menu() {
    }
    public Menu(int id,LocalDate date) {
        this.id=id;
        this.date = date;
    }

    public Menu(int id,LocalDate date, Set<Restaurants> restaurants) {
        this.id=id;
        this.date = date;
        this.restaurants = restaurants;
    }
    public Menu(LocalDate date, Restaurants restaurants) {
        this.date = date;
        this.restaurants = new HashSet<>(Collections.singletonList(restaurants));
    }
    public Menu(LocalDate date, Collection<Restaurants> restaurants) {
        this.date = date;
        this.restaurants = new HashSet<>(restaurants);
    }

    public Menu(int id,LocalDate date, Collection<Restaurants> restaurants) {
        this.id=id;
        this.date = date;
        this.restaurants = new HashSet<>(restaurants);
    }

    public Menu(LocalDate date) {
        this.date = date;
    }

    public Menu(LocalDate date,Integer[] restaurants) {
        this.date = date;
        this.restaurants = Arrays.stream(restaurants).map(Restaurants::new).collect(Collectors.toSet());

    }

    public Menu(Integer[] restaurants) {
        this.restaurants = Arrays.stream(restaurants).map(Restaurants::new).collect(Collectors.toSet());
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Set<Restaurants> getRestaurants() {
        return restaurants;
    }
    public Restaurants getRestaurant() {
        return restaurants.iterator().next();
    }
    public void setRestaurants(Set<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }
    public void setRestaurant(Restaurants restaurants) {
        this.restaurants = new HashSet<>(Collections.singletonList(restaurants));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        return (getId() != null ? getId().equals(menu.getId()) : menu.getId() == null) && (getDate() != null ? getDate().equals(menu.getDate()) : menu.getDate() == null);
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
