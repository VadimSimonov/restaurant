package restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restaurant.controllers.user.AuthorizedUser;
import restaurant.service.MenuService;
import restaurant.service.RestaurantService;
import restaurant.service.UserService;
import restaurant.service.VoteService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request ,Model model) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/restaurants")
    public String restaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAll());
        return "restaurants";
    }

    @GetMapping("/menu")
    public String voite(Model model) {
        model.addAttribute("menu", menuService.getAll());
        return "menu";
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
