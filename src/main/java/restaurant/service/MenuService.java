package restaurant.service;

import restaurant.model.Menu;
import restaurant.model.User;

import java.util.List;

public interface MenuService {

    Menu create(Menu menu,int restaurantId);

    void delete(int id);

    Menu get(int id,int restaurantId);

    void update(Menu menu,int restaurantId);

    List<Menu> getAll();
}
