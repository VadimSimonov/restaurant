package restaurant.repository;

import restaurant.model.Meals;
import restaurant.model.User;

import java.util.List;

public interface MealRepository {
    Meals save(Meals meals,int restaurantId);

    boolean delete(int id);

    Meals get(int id,int restaurantId);

    List<Meals> getAll();

    List<Meals> getAllByRestaurantId(int restaurantId);
}
