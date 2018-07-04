package restaurant.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import restaurant.model.User;
import restaurant.util.UtilsBinding;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @Autowired
    private MessageSource messageSource;

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
    public ResponseEntity<String> createOrUpdate(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return UtilsBinding.checkBinding(result);
        }
        if (user.isNew()) {
            try {
                super.create(new User(user));
            } catch (NoResultException e) {
                return new ResponseEntity<>(messageSource.getMessage("common.choise.role",
                        null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
            } catch (DataIntegrityViolationException ex) {
                return new ResponseEntity<>(messageSource.getMessage("common.duble.email",
                        null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else
            try {
                super.update(user, user.getId());
            } catch (NoResultException e) {
                return new ResponseEntity<>(messageSource.getMessage("common.choise.role",
                        null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
            } catch (DataIntegrityViolationException ex) {
                return new ResponseEntity<>(messageSource.getMessage("common.duble.email",
                        null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
            }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/{id}")
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.enable(id, enabled);
    }

}
