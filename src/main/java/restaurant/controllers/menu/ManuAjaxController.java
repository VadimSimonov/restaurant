package restaurant.controllers.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Meals;
import restaurant.model.Menu;
import restaurant.model.Restaurants;
import restaurant.model.Vote;
import restaurant.service.MealService;
import restaurant.service.MenuService;
import restaurant.service.RestaurantService;
import restaurant.service.VoteService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/menu")
public class ManuAjaxController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @Autowired
    private MealService mealService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public List<Menu> getAll() {
        log.info("getAll {}");
        return menuService.getAll();
    }

    @PostMapping(value = "/{id}")
    public void ratingPlusMinus(@RequestParam("userId") String userId,@RequestParam("pm") String pm,@PathVariable("id") int restaurantId) {
        log.info("ratingPlusMinus {}", userId);
        LocalDateTime localDateTime = LocalDateTime.now();
        Vote vote=new Vote(localDateTime,pm.equals("")?null:Integer.valueOf(pm));
        voteService.ratingVote(vote,Integer.valueOf(userId),restaurantId);
    }

    @GetMapping(value = "/{id}")
    public List<Meals> getMealsByRestaurantId(@PathVariable("id") int restaurantId) {
        log.info("get meal by restaurant id {}", restaurantId);
        return mealService.getAllByRestaurantId(restaurantId);
    }

    @DeleteMapping("/ajax/admin/restaurants/meals/{id}")
    public void deleteMenu(@PathVariable("id") int meal_id) {
        log.info("delete {}", meal_id);
        mealService.delete(meal_id);
    }

}
