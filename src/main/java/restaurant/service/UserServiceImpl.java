package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurant.model.User;
import restaurant.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
}
