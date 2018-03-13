package restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import restaurant.model.Restaurants;

import java.util.List;

import static restaurant.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceImplTest implements AbstractServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void create() throws Exception {
        restaurantService.create(CreateRestaurants);
        assertMatchRestaurants(restaurantService.getAll(),CreateRestaurants,Restaurant1,Restaurant2);
    }

    @Test
    public void delete() throws Exception {
        restaurantService.delete(Restaurant_ID1);
        assertMatchRestaurants(restaurantService.getAll(),Restaurant2);
    }

    @Test
    public void get() throws Exception {
        Restaurants restaurant = restaurantService.get(Restaurant_ID1);
        assertMatchRestaurants(restaurant,Restaurant1);
    }

    @Test
    public void update() throws Exception {
        Restaurants updated = new Restaurants(Restaurant1);
        updated.setName("UpdatedName");
        updated.setId(Restaurant_ID1);
        restaurantService.update(updated);
        assertMatchRestaurants(restaurantService.getAll(),Restaurant2,UpdateRestaurants);
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurants> restaurant = restaurantService.getAll();
        assertMatchRestaurants(restaurant,Restaurant1,Restaurant2);
    }

}