package restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import restaurant.util.UtilId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurants")
    private Set<Meals> meals;

   // @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurants")
  //  private Set<Vote> votes;


 //   @ManyToMany(mappedBy = "restaurants")
 //   private Set<Menu> menu;
/*
    //@JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Menu> menu;
*/
    public Restaurants() {
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
  /*
    public Set<Vote> getVotes() {
        return votes;
    }
    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
*/
  /*  public Set<Menu> getMenu() {
        return menu;
    }
    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
    }
*/
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
