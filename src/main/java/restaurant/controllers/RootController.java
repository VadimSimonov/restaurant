package restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import restaurant.controllers.user.AbstractUserController;
import restaurant.model.User;
import restaurant.web.AuthorizedUser;
import restaurant.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController {
    @Autowired
    private UserService userService;

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
    public String saveRegister(@Valid User user, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        } else {
            super.create(User.createNewFromTo(user));
            status.setComplete();
            return "redirect:login?message=app.registered&username=" + user.getEmail();
            }
    }
}
