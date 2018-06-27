package restaurant.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @Autowired
    private MessageSource messageSource;

    @Override
    @Transactional
    public Vote save(Vote vote, int restaurantId,int userId) {
        if (!vote.isNew() && get(vote.getId(),restaurantId) == null) {
            return null;
        }
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
        List exist = em.createNamedQuery(Vote.getDate, Vote.class)
                .setParameter("sdate", vote.getDate_time().toLocalDate())
                .setParameter("user_id", userId)
                .getResultList();
            vote.setRestaurants(em.getReference(Restaurants.class, restaurantId));
            vote.setUser(em.getReference(User.class,userId));
        if (exist.size() != 0)
        {
            Vote existVote = (Vote) exist.get(0);
            vote.setId(existVote.getId());
            em.merge(vote);
                return vote;
        }else
            em.persist(vote);
            return vote;
    }

    @Override
    public Vote get(int id,int restaurantId) {
        return em.find(Vote.class, id);
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.getAllSorted, Vote.class).getResultList();
    }

}
