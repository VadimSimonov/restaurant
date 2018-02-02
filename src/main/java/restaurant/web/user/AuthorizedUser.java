package restaurant.web.user;

import restaurant.model.User;

public class AuthorizedUser {
    private static int id = User.START_SEQ;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}
