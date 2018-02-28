package restaurant.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Restaurants;
import restaurant.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class jpaRestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurants save(Restaurants restaurants) {
        if (!restaurants.isNew() && get(restaurants.getId()) == null) {
            return null;
        }
        if (restaurants.isNew()) {
            em.persist(restaurants);
            return restaurants;
        } else {
            return em.merge(restaurants);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurants.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Restaurants get(int id) {
        return em.find(Restaurants.class, id);
    }

    @Override
    public List<Restaurants> getAll() {
        return em.createNamedQuery(Restaurants.ALL_SORTED, Restaurants.class).getResultList();
    }
}
