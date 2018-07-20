package restaurant.controllers.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurant.model.Meals;
import restaurant.model.Restaurants;
import restaurant.service.MealService;
import restaurant.service.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
public class RestaurantRestController {
    static final String REST_URL = "/rest/admin/restaurants";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MealService mealService;

    @GetMapping(value = "/rest/admin/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurants> getAll() {
        log.info("getAll {}");
        return restaurantService.getAll();
    }

    @GetMapping(value = "/rest/admin/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurants get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }

    @DeleteMapping("/rest/admin/restaurants/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        restaurantService.delete(id);
    }

    @PostMapping("/rest/admin/restaurants")
    public ResponseEntity<Restaurants> create(@RequestBody Restaurants restaurants) {
        log.info("create {}", restaurants);
        Restaurants created = restaurantService.create(new Restaurants(restaurants));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/rest/admin/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurants> restUpdate(@RequestBody Restaurants restaurants, @PathVariable("id") int id) {
        log.info("update {}", restaurants);
        restaurants.setId(id);
        restaurantService.update(new Restaurants(restaurants));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurants.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurants);
    }

    @PostMapping("/rest/admin/restaurants/{id}/meals")
    public ResponseEntity<Meals> createMeals(@RequestBody Meals meals, @PathVariable("id") int restaurantId) {
        log.info("create meal {}", meals);
        mealService.create(meals, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(meals.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(meals);
    }

    @PutMapping(value = "/rest/admin/restaurants/{id}/meals/{mealId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meals> restMealUpdate(@RequestBody Meals meals, @PathVariable("id") int id, @PathVariable("mealId") int mealId) {
        log.info("update meal{}", meals);
        meals.setId(mealId);
        mealService.update(meals, id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(meals.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(meals);
    }

    @GetMapping("/rest/admin/restaurants/{id}/meals")
    public List<Meals> getMealsFromRestaurant(@PathVariable("id") int restaurantId) {
        log.info("get meal by restaurant id {}", restaurantId);
        return mealService.getAllByRestaurantId(restaurantId);
    }

    @GetMapping("/rest/admin/restaurants/{id}/meal{mealId}")
    public Meals getMeal(@PathVariable("id") int restaurantId, @PathVariable("mealId") int mealId) {
        log.info("get mealId=", mealId, "get restaurantId=", restaurantId);
        return mealService.get(mealId, restaurantId);
    }

    @DeleteMapping("/rest/admin/restaurants/meals/{id}")
    public void deleteMeal(@PathVariable("id") int meal_id) {
        log.info("delete {}", meal_id);
        mealService.delete(meal_id);
    }

}
