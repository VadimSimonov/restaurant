package restaurant.service;

import restaurant.model.Menu;
import restaurant.model.User;

import java.util.List;

public interface MenuService {

    void delete(int id);

    Menu get(int id,int restaurantId);

    Menu update(Menu menu,Integer[] restaurantId);

    List<Menu> getAll();

    Menu create(Menu menu,Integer[] selected);
}
