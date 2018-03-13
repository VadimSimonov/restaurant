package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurant.model.Menu;
import restaurant.model.Vote;
import restaurant.repository.MenuRepository;
import restaurant.repository.VoteRepository;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;


    public Vote create(Vote vote, int restaurantId, int userId) {
        Assert.notNull(vote, "menu must not be null");
        return repository.save(vote,restaurantId,userId);
    }


    public void delete(int id) {
        repository.delete(id);
    }


    public Vote get(int id,int restaurantId) {
        return repository.get(id,restaurantId);
    }


    public void update(Vote vote,int restaurantId,int userId) {
        repository.save(vote, restaurantId,userId);
    }

    public List<Vote> getAll() {
        return repository.getAll();
    }
}
