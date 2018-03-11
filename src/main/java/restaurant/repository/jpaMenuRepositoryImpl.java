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
    @Transactional
    public Menu save(Menu menu) {
        if (!menu.isNew() && get(menu.getId()) == null) {
            return null;
        }
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.getAllSorted, Menu.class).getResultList();
    }

}
