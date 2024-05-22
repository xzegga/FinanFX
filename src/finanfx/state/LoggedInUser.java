package finanfx.state;

import finanfx.models.User;

/**
 *
 * @author Raul Escamilla
 */
public class LoggedInUser {
    private static LoggedInUser instance;
    private User user;

    private LoggedInUser() {
        // Private constructor to prevent instantiation
    }

    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
