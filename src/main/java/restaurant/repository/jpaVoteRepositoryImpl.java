package restaurant.repository;

import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Restaurants;
import restaurant.model.User;
import restaurant.model.Vote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
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
        LocalTime time = vote.getDate_time().toLocalTime();
        LocalTime after11 = LocalTime.of(21, 0, 0, 0);
        LocalDate dateNow = vote.getDate_time().toLocalDate();
    //    LocalDate dateN=LocalDate.of(2018,02,20);
        List exist = em.createNamedQuery(Vote.getDate, Vote.class)
                .setParameter("sdate", dateNow)
                .setParameter("user_id", userId)
                .getResultList();
        if (time.isAfter(after11))
        {
            return null;
        }else
            vote.setRestaurants(em.getReference(Restaurants.class, restaurantId));
            vote.setUser(em.getReference(User.class,userId));
        if (time.isBefore(after11) && exist.size()!=0)
        {
            Vote existVote = (Vote) exist.get(0);
            vote.setId(existVote.getId());
            em.merge(vote);
                return vote;
        }else
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
