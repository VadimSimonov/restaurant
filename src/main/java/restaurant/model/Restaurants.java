package restaurant.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurants {
    public static final int START_SEQ = 100000;

    public static final String Restaurant1="Restaurant1";
    public static final String Restaurant2="Restaurant2";
    @Id
    @Column(name="id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;

    public Restaurants() {
    }

    public Restaurants(String name) {
        this.name = name;
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
}
