package restaurant;

import org.springframework.test.web.servlet.ResultMatcher;
import restaurant.model.Meals;
import restaurant.model.Restaurants;
import restaurant.model.User;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static restaurant.model.User.START_SEQ;


public class MealTestData {
    public static final int Meals_ID = START_SEQ+2;
    public static final int newMeals_ID = START_SEQ +3;
    public static final int Restaurant_ID = 100007;
    public static final int newRestaurant_ID = 100008;

    public static final Meals MEAL1 =new Meals(Meals_ID,"Zavtrak",new Restaurants(Restaurants.Restaurant1),
            of(2018, Month.MAY, 30, 13, 0),500);
    public static final Meals MEAL2 = new Meals(newMeals_ID,"Obed", new Restaurants(Restaurants.Restaurant2),
            of(2018, Month.MAY, 29, 13, 0),700);
    public static final List<Meals> MEALS = Arrays.asList(MEAL2, MEAL1);

    public static void assertMatch(Meals actual, Meals expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "meal", "price");
    }

    public static void assertMatch(Iterable<Meals> actual, Meals... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meals> actual, Iterable<Meals> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("Restaurants").isEqualTo(expected);
    }
}
