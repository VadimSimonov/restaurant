package restaurant.service;

import restaurant.model.Meals;
import restaurant.model.User;

import java.util.List;

public interface MealService {

    Meals create(Meals meals, int restaurantId);

    void delete(int id);

    Meals get(int id, int restaurantId);

    Meals update(Meals meals, int restaurantId);

    List<Meals> getAll();

    List<Meals> getAllByRestaurantId(int restaurantId);

}
