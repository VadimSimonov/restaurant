package restaurant;

import restaurant.model.Meals;
import restaurant.model.Restaurants;
import restaurant.model.User;

import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static restaurant.model.User.START_SEQ;

public class MealTestData {
    public static final int Meals_ID = START_SEQ;
    public static final int newMeals_ID = START_SEQ + 1;
    public static final int Restaurant_ID = 100007;
    public static final int newRestaurant_ID = 100008;

    public static final Meals meals =new Meals("Zavtrak",new Restaurants("Restaurant1"),
            of(2018, Month.MAY, 30, 13, 0),500);
    public static final Meals newMeals = new Meals("Obed", new Restaurants("Restaurant2"),
            of(2018, Month.MAY, 29, 13, 0),700);

    public static void assertMatch(Meals actual, Meals expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "meal", "price");
    }

    public static void assertMatch(Iterable<Meals> actual, Meals... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meals> actual, Iterable<Meals> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("meal", "price").isEqualTo(expected);
    }
}
