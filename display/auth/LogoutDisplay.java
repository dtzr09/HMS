package display.auth;

import display.WelcomeDisplay;

public class LogoutDisplay {
    public static void logout() {
        WelcomeDisplay.welcome();
        System.exit(0);
    }
}
