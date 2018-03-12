package restaurant;

import restaurant.model.Menu;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static restaurant.MealTestData.Restaurant1;
import static restaurant.MealTestData.RestaurantInDb1;
import static restaurant.MealTestData.RestaurantsList;


public class MenuTestData {
    public static final int Menu_ID1 = 100030;
    public static final int Menu_ID2 = 100031;
    public static final int Menu_ID3 = 100032;

    public static final Menu MENU1=new Menu(Menu_ID1, LocalDate.of(2018,02,20));
    public static final Menu MENU2=new Menu(Menu_ID2,LocalDate.of(2018,02,21));
    public static final Menu CREATEMENU=new Menu(LocalDate.of(2019,02,21));

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurants");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurants").isEqualTo(expected);
    }


}
