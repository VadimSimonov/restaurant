package restaurant.controllers.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Meals;
import restaurant.model.Menu;
import restaurant.model.Vote;
import restaurant.service.MealService;
import restaurant.service.MenuService;
import restaurant.service.VoteService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/ajax/admin/menu")
public class MenuAjaxController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @Autowired
    private MealService mealService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    public List<Menu> getAll() {
        log.info("getAll {}");
        return menuService.getAll();
    }
    @PostMapping
    public ResponseEntity<String> addMenu(@RequestParam(value = "selected[]", required = false) Integer[] selected) {
        log.info("addMenu {}", (Object[]) selected);
        try {
            Menu menu = new Menu(LocalDate.now(), selected);
            menuService.create(menu, selected);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(messageSource.getMessage("common.set.menu",
                    null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<String> ratingVote(@RequestParam("userId") String userId, @RequestParam("pm") String pm, @PathVariable("id") int restaurantId) {
        log.info("ratingVote {}", userId);
        LocalDateTime localDateTime = LocalDateTime.now();
        Vote vote=new Vote(localDateTime,pm.equals("")?null:Integer.valueOf(pm));
        LocalTime time = vote.getDate_time().toLocalTime();
        LocalTime after11 = LocalTime.of(11, 0, 0, 0);
        if (time.isAfter(after11)) {
            return new ResponseEntity<>(messageSource.getMessage("common.time.vote",
                    null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
        } else
            voteService.ratingVote(vote,Integer.valueOf(userId),restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
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
