package restaurant.controllers.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurant.model.Menu;
import restaurant.model.Vote;
import restaurant.service.MenuService;
import restaurant.service.VoteService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping
public class MenuRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/admin/menu";

    @Autowired
    private MenuService menuService;

    @Autowired
    private VoteService voteService;

    @GetMapping(value = "/rest/admin/menu")
    public List<Menu> getAll() {
        log.info("getAll {}");
        return menuService.getAll();
    }

    @GetMapping(value = "/restuser/admin/menu")
    public List<Menu> getAllForUser() {
        log.info("getAll {}");
        return menuService.getAll();
    }

    @PostMapping(value = "/rest/admin/menu")
    public ResponseEntity<Menu> addMenu(@RequestBody Integer[] selected) {
        log.info("addMenu {}", (Object[]) selected);
        Menu menu = new Menu(LocalDate.now(), selected);
        Menu created = menuService.create(menu, selected);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/rest/admin/menu/{id}/user/{userId}")
    public ResponseEntity<String> ratingVote(@RequestBody Vote vote, @PathVariable("id") int restaurantId, @PathVariable("userId") int userId) {
        return voteMethod(vote, restaurantId, userId);
    }

    @PostMapping(value = "/restuser/admin/menu/{id}/user/{userId}")
    public ResponseEntity<String> ratingVoteForUser(@RequestBody Vote vote, @PathVariable("id") int restaurantId, @PathVariable("userId") int userId) {
        return voteMethod(vote, restaurantId, userId);

    }

    private ResponseEntity<String> voteMethod(Vote vote, int restaurantId, int userId) {
        log.info("ratingVote {}", vote);
        vote.setDate_time(LocalDateTime.now());
        LocalTime time = vote.getDate_time().toLocalTime();
        LocalTime after11 = LocalTime.of(11, 0, 0, 0);
        if (time.isAfter(after11)) {
            return new ResponseEntity<>("You can vote only until 11 am", new HttpHeaders(), HttpStatus.CONFLICT);
        } else
            voteService.ratingVote(vote, userId, restaurantId);
        return new ResponseEntity<>("Success", new HttpHeaders(), HttpStatus.OK);
    }

}
