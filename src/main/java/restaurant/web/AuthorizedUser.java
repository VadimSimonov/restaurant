package restaurant.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import restaurant.model.User;

import java.util.Collection;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser {//extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private User user;
/*
    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true,  user.);
        this.user = new User(user);
    }
    */

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().user.getId();
    }

    public User getUserTo() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}