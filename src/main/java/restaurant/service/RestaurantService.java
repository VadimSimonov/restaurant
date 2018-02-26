package restaurant.service;

import restaurant.model.Restaurants;
import restaurant.model.User;

import java.util.List;

public interface RestaurantService {

    Restaurants create(Restaurants restaurants);

    void delete(int id);

    Restaurants get(int id);

    void update(Restaurants restaurants);

    List<Restaurants> getAll();
}
