package display.session;

/**
 * The {@code LogoutDisplay} class provides a simple interface for logging out a
 * user.
 * When a user chooses to log out, this class redirects them to the welcome
 * screen
 * and terminates the program.
 */
public class LogoutDisplay {

    /**
     * Logs out the current user by displaying the welcome screen and exiting the
     * program.
     * This method is typically called when a user decides to end their session.
     */
    public static void logout() {
        WelcomeDisplay.welcome();
        System.exit(0);
    }
}
