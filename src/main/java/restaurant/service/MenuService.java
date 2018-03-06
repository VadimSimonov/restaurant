package restaurant.service;

import restaurant.model.Menu;
import restaurant.model.User;

import java.util.List;

public interface MenuService {

    Menu create(Menu menu);

    void delete(int id);

    Menu get(int id);

    void update(Menu menu);

    List<Menu> getAll();
}
