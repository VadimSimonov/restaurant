package restaurant.repository;

import restaurant.model.Meals;
import restaurant.model.Menu;

import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu);

    boolean delete(int id);

    Menu get(int id);

    List<Menu> getAll();

}
