package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurant.model.User;
import restaurant.repository.UserRepository;
import restaurant.web.AuthorizedUser;

import java.util.List;

@Service//("userService")
public class UserServiceImpl implements UserService{//, UserDetailsService {

    @Autowired
    private UserRepository repository;


    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }


    public void delete(int id) {
        repository.delete(id);
    }


    public User get(int id) {
        return repository.get(id);
    }


    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }


    public void update(User user) {
        repository.save(user);
    }


    public List<User> getAll() {
        return repository.getAll();
    }

    /*
    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
    */
}
