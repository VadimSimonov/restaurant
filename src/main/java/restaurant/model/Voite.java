package restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "voite", uniqueConstraints = {@UniqueConstraint(columnNames = "restauran_id", name = "voite_unique_restauran_idx")})
public class Voite {
    public static final int START_SEQ = 100000;

    @Id
    @Column(name="id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;
    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    private Date date_time=new Date();
    @Column(name = "voite", nullable = false, unique = true)
    private Integer voite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restauran_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurants Restaurants;

    public Voite() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getDate_time() {
        return date_time;
    }
    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }
    public Integer getVoite() {
        return voite;
    }
    public void setVoite(Integer voite) {
        this.voite = voite;
    }
    public restaurant.model.Restaurants getRestaurants() {
        return Restaurants;
    }
    public void setRestaurants(restaurant.model.Restaurants restaurants) {
        Restaurants = restaurants;
    }
}
