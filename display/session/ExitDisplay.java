package display.session;

/**
 * The {@code ExitDisplay} class provides functionality to exit the program
 * gracefully.
 * 
 */
public class ExitDisplay {

    /**
     * Exits the program by clearing the console, displaying a "Thank you!" message,
     * and terminating the application.
     */
    public static void exit() {
        ClearDisplay.ClearConsole();
        System.out.println("Thank you!");
        System.exit(0);
    }
}