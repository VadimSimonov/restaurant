package restaurant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u  ORDER BY u.name, u.email"),
        @NamedQuery(name = User.GET_ROLE, query = "SELECT u FROM Role u WHERE u.role LIKE :user_role"),

})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractBaseEntity implements Serializable {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";
    public static final String GET_ROLE = "User.getRole";

    @Column(name = "name", nullable = false)
    @NotBlank
    //@Size(min = 2, max = 100, message = "length must between 2 and 100 characters")
    private String name;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered=new Date();

    @Column(name = "password", nullable = false)
    @Size(min = 5, max = 100)
    @NotBlank
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    public User() {
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.role = role;
    }

    public User(Integer id, String name, String email, String password,  Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRole());
    }
    public User(String name, String password, String email,Role role) {
        this(null, name, email,password, role);
    }
    public static User createNewFromTo(User newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(),newUser.role);
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
    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
                ", role=" + role +
                '}';
    }
}
