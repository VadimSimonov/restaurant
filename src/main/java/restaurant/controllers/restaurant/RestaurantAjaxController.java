package restaurant.controllers.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Restaurants;
import restaurant.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/restaurants")
public class RestaurantAjaxController  {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurants> getAll() {
        log.info("getAll {}");
        List<Restaurants> all = service.getAll();
        return all;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurants get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid Restaurants restaurants) {
        log.info("create {}", restaurants);
        if (restaurants.isNew()) {
            service.create(new Restaurants(restaurants));
        } else {
            service.update(restaurants);
        }
    }

}
