package restaurant.repository;

import restaurant.model.Restaurants;

import java.util.List;

public interface RestaurantRepository {
    Restaurants save(Restaurants restaurants);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurants get(int id);

    List<Restaurants> getAll();
}
