package restaurant.model;

import javax.persistence.*;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User {
    public static final int START_SEQ = 100000;

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Id
    @Column(name="id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered=new Date();
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.roles = roles;
    }

    public User(Integer id, String name, String email, String password,  Role role, Role... roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = EnumSet.of(role,roles);
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRoles());
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRegistered(Date registered) {
        this.registered = registered;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {

        return name;
    }
    public Date getRegistered() {
        return registered;
    }
    public String getPassword() {
        return password;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public String getEmail() {
        return email;
    }
    public Set<Role> getRoles() {

        return roles;
    }
    public boolean isNew() {
        return this.id == null;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registered=" + registered +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
