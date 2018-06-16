package restaurant.controllers.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import restaurant.model.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid User user) {
        if (user.isNew()) {
            super.create(new User(user));
        } else {
            super.update(user, user.getId());
        }
    }

}
