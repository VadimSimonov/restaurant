package restaurant.controllers.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurant.model.Meals;
import restaurant.model.Restaurants;
import restaurant.service.MealService;
import restaurant.service.RestaurantService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.xml.soap.SOAPElement;
import java.util.List;

@RestController
//@RequestMapping("/ajax/admin/restaurants")
public class RestaurantAjaxController  {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MealService mealService;

    @GetMapping(value = "/ajax/admin/restaurants",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurants> getAll() {
        log.info("getAll {}");
        List<Restaurants> all = restaurantService.getAll();
        return all;
    }

    @GetMapping(value = "/ajax/admin/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurants get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }

    @DeleteMapping("/ajax/admin/restaurants/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        restaurantService.delete(id);
    }

    @PostMapping("/ajax/admin/restaurants")
    public void createOrUpdate(@Valid Restaurants restaurants) {
        log.info("create {}", restaurants);
        if (restaurants.isNew()) {
            restaurantService.create(new Restaurants(restaurants));
        } else {
            restaurantService.update(restaurants);
        }
    }

    @PostMapping("/ajax/admin/restaurants/{id}/meals")
    public void createOrUpdateMeals(@RequestParam("id") String id,@RequestParam("meal") String meal,
                                    @RequestParam("price") String price,@PathVariable("id") int restaurantId) {
        Meals meals = new Meals(id.equals("")?null:Integer.valueOf(id),meal,Integer.valueOf(price));
        log.info("create {}", meals);
        if (meals.isNew()) {
            mealService.create(meals,restaurantId);
        } else {
            mealService.update(meals,restaurantId);
        }
    }

    @GetMapping("/ajax/admin/restaurants/{id}/meals")
    public List<Meals> getMeals(@PathVariable("id") int restaurantId) {
        log.info("get meal by restaurant id {}", restaurantId);
        return mealService.getAllByRestaurantId(restaurantId);
    }

    @DeleteMapping("/ajax/admin/restaurants/meals/{id}")
    public void deleteMeal(@PathVariable("id") int meal_id) {
        log.info("delete {}", meal_id);
        mealService.delete(meal_id);
    }

}
