package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import restaurant.model.User;
import restaurant.repository.UserRepository;
import restaurant.web.AuthorizedUser;

import java.util.List;

import static restaurant.util.UserUtil.prepareToSave;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

   // @Autowired
   private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        //return repository.save(user);
        return repository.save(prepareToSave(user, passwordEncoder));
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
       // repository.save(user);
        Assert.notNull(user, "user must not be null");
        repository.save(prepareToSave(user, passwordEncoder));
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    public List<User> getAll() {
        return repository.getAll();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        } else if (!user.isEnabled()) {
            throw new UsernameNotFoundException("User is disabled");
        }
        return new AuthorizedUser(user);
    }

}
