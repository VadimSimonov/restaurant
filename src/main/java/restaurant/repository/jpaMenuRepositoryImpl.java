package restaurant.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Meals;
import restaurant.model.Menu;
import restaurant.model.Restaurants;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class jpaMenuRepositoryImpl implements MenuRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Menu save(Menu menu) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Menu get(int id) {
        return null;
    }

    @Override
    public List<Menu> getAll() {
        return null;
    }

    @Override
    public List<Menu> getAllByRestaurantId(int restaurantId) {
        return null;
    }
}
