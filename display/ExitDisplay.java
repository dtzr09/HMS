package display;

public class ExitDisplay {
    public static void exit() {
        ClearDisplay.ClearConsole();
        System.out.println("Thank you!");
        System.exit(0);
    }
}
