package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurant.model.Menu;
import restaurant.model.User;
import restaurant.repository.MenuRepository;
import restaurant.repository.UserRepository;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;


    public Menu create(Menu menu,Integer[] selected) {
        Assert.notNull(selected, "selected restaurants must not be empty");
        return repository.save(menu,selected);
    }


    public void delete(int id) {
        repository.delete(id);
    }


    public Menu get(int id,int restaurantId) {
        return repository.get(id,restaurantId);
    }


    public Menu update(Menu menu,Integer[] restaurantId) {
        return repository.save(menu, restaurantId);
    }


    public List<Menu> getAll() {
        return repository.getAll();
    }
}
