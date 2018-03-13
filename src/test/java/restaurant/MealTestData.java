package restaurant;

import restaurant.model.Meals;
import restaurant.model.Restaurants;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static restaurant.model.User.START_SEQ;


public class MealTestData {
    public static final Restaurants Restaurant1=new Restaurants("Restaurant1");
    public static final Restaurants Restaurant2=new Restaurants("Restaurant2");
    public static final Restaurants RestaurantInDb1=new Restaurants(100017,"Restaurant1");
    public static final Restaurants RestaurantInDb2=new Restaurants(100018,"Restaurant2");

    public static final List<Restaurants>RestaurantsList= Arrays.asList(Restaurant1,Restaurant2);

    public static final int Meals_ID = START_SEQ;
    public static final int Meals_ID2 = START_SEQ +1;
    public static final int Restaurant_ID1 = 100060;
    public static final int Restaurant_ID2 = 100061;

    public static final Meals MEAL1 =new Meals(Meals_ID,"Zavtrak",Restaurant1,500);
          //  of(2018, Month.MAY, 30, 13, 0),500);
    public static final Meals MEAL2 = new Meals(Meals_ID2,"Obed", Restaurant2,700);
          //  of(2018, Month.MAY, 29, 13, 0),700);
    public static final List<Meals> MEALS = Arrays.asList(MEAL2, MEAL1);

    public static final Meals CreateMEAL = new Meals("newObed",777);
          //  of(2018, Month.MAY, 29, 13, 0),777);

    public static final Meals UpdateMEAL = new Meals(Meals_ID,"UpdateObed",777);


    public static final Restaurants UpdateRestaurants = new Restaurants("UpdatedName");
    public static final Restaurants CreateRestaurants = new Restaurants("CreateRestaurant");


    public static void assertMatch(Meals actual, Meals expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurants");
    }

    public static void assertMatch(Iterable<Meals> actual, Meals... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meals> actual, Iterable<Meals> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurants").isEqualTo(expected);
    }

/*
    public static void assertMatchRestaurants(Restaurants actual, Restaurants expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }
    */

    public static void assertMatchRestaurants(Restaurants actual, Restaurants expected) {
        assertThat(actual).isEqualTo(expected);
    }
    public static void assertMatchRestaurants(Iterable<Restaurants> actual, Restaurants... expected) {
        assertMatchRestaurants(actual, Arrays.asList(expected));

    }

    public static void assertMatchRestaurants(Iterable<Restaurants> actual, Iterable<Restaurants> expected) {
        assertThat(actual).isEqualTo(expected);
        //Assert.assertEquals(actual., expected);
     //   assertThat(actual,is(expected));
       // Assert.assertArrayEquals(expected,actual);
    }

}
