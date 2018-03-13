package restaurant.repository;

import restaurant.model.Vote;

import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote, int restaurantId,int userId);

    boolean delete(int id);

    Vote get(int id, int restaurantId);

    List<Vote> getAll();

}
