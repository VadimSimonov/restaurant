package restaurant;

import restaurant.model.Role;
import restaurant.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static restaurant.model.User.START_SEQ;

public class UserTestData {
    public static final int USER_ID = 100040;
    public static final int ADMIN_ID = 100041;
    public static final int NEWUSER_ID = 100000 + 3;
    public static final Role ROLE_USER=new Role(100020,"ROLE_USER");
    public static final Role ROLE_ADMIN=new Role(100021,"ROLE_ADMIN");

    public static final User USER =new User(USER_ID, "User", "user@yandex.ru", "password", ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "role");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "role").isEqualTo(expected);
    }
}
