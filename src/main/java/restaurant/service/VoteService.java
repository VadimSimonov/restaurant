package restaurant.service;

import restaurant.model.Vote;

import java.util.List;

public interface VoteService {

    Vote create(Vote vote, int restaurantId,int userId);

    void delete(int id);

    Vote get(int id, int restaurantId);

    void update(Vote menu, int restaurantId,int userId);

    Vote ratingVote(Vote vote,int userId,int restaurantId);

    List<Vote> getAll();
}
