package restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import restaurant.model.Meals;
import java.util.List;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static restaurant.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceImplTest implements AbstractServiceTest {
    @Autowired
    private MealService service;

    @Test
    public void create() throws Exception {
        service.create(CreateMEAL, Restaurant_ID1);
        assertMatch(service.getAll(),CreateMEAL,MEAL2,MEAL1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(Meals_ID2, Restaurant_ID2);
        assertMatch(service.getAll(),MEAL1);
    }

    @Test
    public void get() throws Exception {
        Meals meal = service.get(Meals_ID, Restaurant_ID1);
        assertMatch(meal,MEAL1);
    }

    @Test
    public void update() throws Exception {
        Meals meal = service.update(UpdateMEAL, Restaurant_ID1);
        assertMatch(service.get(Meals_ID, Restaurant_ID1),meal);
    }

    @Test
    public void getAll() throws Exception {
        List<Meals> all = service.getAll();
        assertMatch(all,MEALS);

    }

    @Test
    public void getAllByRestaurantId()throws Exception {
        List<Meals> all = service.getAllByRestaurantId(Restaurant_ID1);
        assertMatch(all,MEAL1);
    }

}