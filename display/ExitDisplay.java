package display;

public class ExitDisplay {
    /**
     * Exits the program.
     */
    public static void exit() {
        ClearDisplay.ClearConsole();
        System.out.println("Thank you!");
        System.exit(0);
    }
}
