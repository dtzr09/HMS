package display;

public class ExitDisplay {
    public static void exitDisplay() {
        ClearDisplay.ClearConsole();
        System.out.println("Thank you!");
        System.exit(0);
    }
}
