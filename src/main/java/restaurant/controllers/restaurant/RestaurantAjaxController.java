package restaurant.controllers.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Meals;
import restaurant.model.Restaurants;
import restaurant.service.MealService;
import restaurant.service.RestaurantService;
import restaurant.util.UtilsBinding;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
public class RestaurantAjaxController  {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MealService mealService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/ajax/admin/restaurants",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurants> getAll() {
        log.info("getAll {}");
        return restaurantService.getAll();
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
    public ResponseEntity<String> createOrUpdate(@Valid Restaurants restaurants, BindingResult result) {
        log.info("create {}", restaurants);
        if (result.hasErrors()) {
            return UtilsBinding.checkBinding(result);
        }
        if (restaurants.isNew()) {
            restaurantService.create(new Restaurants(restaurants));
        } else {
            restaurantService.update(restaurants);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ajax/admin/restaurants/{id}/meals")
    // @Validated
    public ResponseEntity<String> createOrUpdateMeals(@RequestParam("id") String id,
                                                      @RequestParam("meal") String meal,
                                    @RequestParam("price") String price,@PathVariable("id") int restaurantId) {
        Meals meals;
        try {
            meals = new Meals(id.equals("") ? null : Integer.valueOf(id), meal, Integer.valueOf(price));
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(messageSource.getMessage("common.set.price",
                    null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (meal.equals("") || meal.length() < 2) {
            return new ResponseEntity<>(messageSource.getMessage("common.set.meal",
                    null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (price.equals("") || price.length() < 2) {
            return new ResponseEntity<>(messageSource.getMessage("common.set.price",
                    null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
        }


        log.info("create {}", meals);
        if (meals.isNew()) {

            mealService.create(meals,restaurantId);
        } else {
            mealService.update(meals,restaurantId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ajax/admin/restaurants/{id}/meals")
    public List<Meals> getMeals(@PathVariable("id") int restaurantId) {
        log.info("get meal by restaurant id {}", restaurantId);
        return mealService.getAllByRestaurantId(restaurantId);
    }

    @GetMapping("/ajax/admin/restaurants/{id}/meal{mealId}")
    public Meals getMeal(@PathVariable("id") int restaurantId, @PathVariable("mealId") int mealId) {
        log.info("get mealId=", mealId, "get restaurantId=", restaurantId);
        return mealService.get(mealId, restaurantId);
    }

    @DeleteMapping("/ajax/admin/restaurants/meals/{id}")
    public void deleteMeal(@PathVariable("id") int meal_id) {
        log.info("delete {}", meal_id);
        mealService.delete(meal_id);
    }

}
