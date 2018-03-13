package restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import restaurant.model.Vote;

import java.util.List;

import static org.junit.Assert.*;
import static restaurant.MealTestData.Restaurant_ID1;
import static restaurant.MealTestData.Restaurant_ID2;
import static restaurant.UserTestData.USER_ID;
import static restaurant.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceImplTest implements AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void create() throws Exception {
        service.create(CREATEVOTE,Restaurant_ID2,USER_ID);
        assertMatch(service.getAll(),VOTE1,VOTE2,CREATEVOTE);
    }

    @Test
    public void delete() throws Exception {
        service.delete(VOTE_ID3);
        assertMatch(service.getAll(),VOTE1,VOTE2);
    }

    @Test
    public void get() throws Exception {
        Vote get = service.get(VOTE_ID1, Restaurant_ID1);
        assertMatch(get,VOTE1);
    }

    @Test
    public void update() throws Exception {
        Vote update = VOTE2;
        update.setVote(0);
        service.update(update,Restaurant_ID2,USER_ID);
        assertMatch(service.getAll(),VOTE1,update);
    }

    @Test
    public void getAll() throws Exception {
        List<Vote> all = service.getAll();
        assertMatch(all,VOTE1,VOTE2);
    }

}