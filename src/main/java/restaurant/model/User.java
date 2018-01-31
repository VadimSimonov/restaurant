package restaurant.model;

import java.util.Date;

public class User {
    private Integer id;
    private String name;
    private Date registered;
    private String password;
    private boolean enabled = true;
    private String email;

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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", registered=" + registered +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean isNew() {
        return this.id == null;
    }
}
