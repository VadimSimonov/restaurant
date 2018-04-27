package restaurant.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Menu save(Menu menu,Integer[] selected) {
        em.createQuery("DELETE FROM Menu")
        .executeUpdate();
        em.persist(menu);
        return menu;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Menu get(int id,int restaurantId) {
        Menu menu = em.find(Menu.class, id);
        return menu != null && menu.getRestaurant().getId() == restaurantId ? menu : null;
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.getAllSorted, Menu.class).getResultList();
    }

}
