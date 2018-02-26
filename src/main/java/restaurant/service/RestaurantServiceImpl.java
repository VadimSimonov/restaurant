package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurant.model.Restaurants;
import restaurant.model.User;
import restaurant.repository.RestaurantRepository;
import restaurant.repository.UserRepository;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurants create(Restaurants restaurants) {
        Assert.notNull(restaurants, "user must not be null");
        return repository.save(restaurants);
    }

    public void delete(int id) {
        repository.delete(id);
    }


    public Restaurants get(int id) {
        return repository.get(id);
    }

    @Override
    public void update(Restaurants restaurants) {
        repository.save(restaurants);
    }

    public List<Restaurants> getAll() {
        return repository.getAll();
    }
}
