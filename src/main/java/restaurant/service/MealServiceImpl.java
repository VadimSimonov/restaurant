package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurant.model.Meals;
import restaurant.repository.MealRepository;
import restaurant.repository.UserRepository;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meals create(Meals meals, int restaurantId) {
        Assert.notNull(meals, "meal must not be null");
        return repository.save(meals, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) {
        repository.delete(id,restaurantId);
    }

    @Override
    public Meals get(int id, int restaurantId) {
        return repository.get(id, restaurantId);
    }

    @Override
    public Meals update(Meals meals, int restaurantId) {
        return repository.save(meals, restaurantId);
    }

    @Override
    public List<Meals> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Meals> getAllByRestaurantId(int restaurantId) {
        return repository.getAllByRestaurantId(restaurantId);
    }
}
