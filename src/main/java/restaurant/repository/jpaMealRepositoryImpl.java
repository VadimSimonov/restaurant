package restaurant.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Meals;
import restaurant.model.Restaurants;
import restaurant.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class jpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Meals save(Meals meal,int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurants(em.getReference(Restaurants.class, restaurantId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return em.createNamedQuery(Meals.DELETE)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    public Meals get(int id, int restaurantId) {
        Meals meal = em.find(Meals.class, id);
        return meal != null && meal.getRestaurants().getId() == restaurantId ? meal : null;
    }

    @Override
    public List<Meals> getAll() {
        return em.createNamedQuery(Meals.ALL_SORTED, Meals.class)
                .getResultList();
    }

    @Override
    public List<Meals> getAllByRestaurantId(int restaurantId) {
        return em.createNamedQuery(Meals.ALL_SORTEDBYID, Meals.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }
}
