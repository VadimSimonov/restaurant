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


    public Menu create(Menu menu,int restaurantId) {
        Assert.notNull(menu, "user must not be null");
        return repository.save(menu,restaurantId);
    }


    public void delete(int id) {
        repository.delete(id);
    }


    public Menu get(int id,int restaurantId) {
        return repository.get(id,restaurantId);
    }


    public void update(Menu menu,int restaurantId) {
        repository.save(menu, restaurantId);
    }


    public List<Menu> getAll() {
        return repository.getAll();
    }
}
