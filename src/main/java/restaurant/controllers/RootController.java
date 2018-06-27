package restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import restaurant.controllers.user.AbstractUserController;
import restaurant.model.User;
import restaurant.service.UserService;
import restaurant.util.UtilsBinding;
import restaurant.web.AuthorizedUser;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class RootController extends AbstractUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String root() {
        return "redirect:menu";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping("/profile")
    public String profile(ModelMap model) {
        model.addAttribute("id", AuthorizedUser.id());
        return "profile";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request ,Model model) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurants")
    public String restaurants() {
        return "restaurants";
    }

    @GetMapping("/menu")
    public String voite(Model model) {
        model.addAttribute("user_id", AuthorizedUser.id());
        return "menu";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new User());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveRegister(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return UtilsBinding.checkBinding(result);
        }
        try {
            super.create(User.createNewFromTo(user));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {

            return new ResponseEntity<>(messageSource.getMessage("common.duble.email",
                    null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (NoResultException e) {
            return new ResponseEntity<>(messageSource.getMessage("common.choise.role",
                    null, new Locale(Locale.getDefault().getLanguage())), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
