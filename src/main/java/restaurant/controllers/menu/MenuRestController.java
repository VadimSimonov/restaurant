package restaurant.controllers.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurant.model.Menu;
import restaurant.model.Vote;
import restaurant.service.MealService;
import restaurant.service.MenuService;
import restaurant.service.VoteService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MenuRestController.REST_URL)
public class MenuRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/admin/menu";

    @Autowired
    private MenuService menuService;

    @Autowired
    private MealService mealService;

    @Autowired
    private VoteService voteService;

    @GetMapping()
    public List<Menu> getAll() {
        log.info("getAll {}");
        return menuService.getAll();
    }

    @PostMapping
    public ResponseEntity<Menu> addMenu(@RequestBody Integer[] selected) {
        log.info("addMenu {}", (Object[]) selected);
        Menu menu = new Menu(LocalDate.now(), selected);
        Menu created = menuService.create(menu, selected);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/{id}/user/{userId}")
    public ResponseEntity<String> ratingVote(@RequestBody Vote vote, @PathVariable("id") int restaurantId, @PathVariable("userId") int userId) {
        log.info("ratingVote {}", vote);
        //LocalDateTime localDateTime = LocalDateTime.now();
        //Vote vote=new Vote(localDateTime,pm.equals("")?null:Integer.valueOf(pm));
        vote.setDate_time(LocalDateTime.now());
        LocalTime time = vote.getDate_time().toLocalTime();
        LocalTime after11 = LocalTime.of(11, 0, 0, 0);
        if (time.isAfter(after11)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else
            voteService.ratingVote(vote, userId, restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
