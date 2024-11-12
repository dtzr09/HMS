package display.auth;

import display.WelcomeDisplay;

public class LogoutDisplay {
    /**
     * Logs out the user
     */
    public static void logout() {
        WelcomeDisplay.welcome();
        System.exit(0);
    }
}
