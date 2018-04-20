package restaurant.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Restaurants;
import restaurant.model.User;
import restaurant.model.Vote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class jpaVoteRepositoryImpl implements VoteRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Vote save(Vote vote, int restaurantId,int userId) {
        if (!vote.isNew() && get(vote.getId(),restaurantId) == null) {
            return null;
        }
     //   vote.setRestaurants(em.getReference(Restaurants.class, restaurantId));
        vote.setUser(em.getReference(User.class,userId));
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public Vote ratingVote(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getId(),restaurantId) == null) {
            return null;
        }
        vote.setRestaurants(em.getReference(Restaurants.class, restaurantId));
        vote.setUser(em.getReference(User.class,userId));
        if (vote.isNew())
            em.persist(vote);
            return vote;
    }

    @Override
    public Vote get(int id,int restaurantId) {
        Vote vote = em.find(Vote.class, id);
        return vote != null ? vote : null;
                //&& vote.getRestaurants().getId() == restaurantId

    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.getAllSorted, Vote.class).getResultList();
    }

}
