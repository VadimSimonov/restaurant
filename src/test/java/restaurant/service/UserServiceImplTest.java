package restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import restaurant.model.Role;
import restaurant.model.User;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration({
        "classpath:spring/spring-config.xml"
})

@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceImplTest {
    private static final int USER_ID = 100000;
    private static final int ADMIN_ID = 100000 + 1;
    private static final int NEWUSER_ID = 100000 + 2;

    public static final User USER =new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    @Autowired
    private UserService service;

    @Test
    public void create() throws Exception {
        User newUser = new User(NEWUSER_ID,"New","new@mail.ru","passNew",Role.ROLE_USER);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }


    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }


    private void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch2(actual, Arrays.asList(expected));
    }
    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch2(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }


}