package restaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import restaurant.util.UtilId;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Restaurants.DELETE, query = "DELETE FROM Restaurants r WHERE r.id=:id"),
        @NamedQuery(name = Restaurants.ALL_SORTED, query = "SELECT r FROM Restaurants r  ORDER BY r.name"),
})
@Entity
@Table(name = "restaurants")
public class Restaurants implements UtilId {
    public static final int START_SEQ = 100000;

    public static final String DELETE = "Restaurants.delete";
    public static final String ALL_SORTED = "Restaurants.getAllSorted";

    @Id
    @Column(name="id", nullable = false)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurants", cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private Set<Meals> meals;

    public Restaurants() {
    }

    public Restaurants(Integer id) {
        this.id=id;
    }

    public Restaurants(String name) {
        this.name = name;
    }

    public Restaurants(Restaurants restaurant) {
        this.id=restaurant.getId();
        this.name=restaurant.getName();
    }
    public Restaurants(int id,String name) {
        this.id=id;
        this.name=name;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Meals> getMeals() {
        return meals;
    }
    public void setMeals(Set<Meals> meals) {
        this.meals = meals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurants that = (Restaurants) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Restaurants{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
