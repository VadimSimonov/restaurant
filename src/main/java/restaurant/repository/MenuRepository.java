package restaurant.repository;

import restaurant.model.Meals;
import restaurant.model.Menu;

import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu, int restaurantId);

    boolean delete(int id);

    Menu get(int id,int restaurantId);

    List<Menu> getAll();

}
