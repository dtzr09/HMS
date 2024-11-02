package AccountManager;

public class AccountManager {
    private String defaultPassword = "password";
    private User loggedInUser;

    public User login(String id, String password) {
        User user = new User(UserRole.PATIENT, id, defaultPassword, "John Doe", "john@example.com");

        if (user.getId().equals(id) && user.getPassword().equals(password)) {
            loggedInUser = user;
            return loggedInUser;
        }
        return null;
    }

    public void logout() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}

