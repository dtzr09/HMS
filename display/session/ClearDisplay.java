package display.session;

/**
 * The {@code ClearDisplay} class provides a utility method to clear the console
 * screen.
 * This class detects the operating system and executes the appropriate command
 * to clear the console on both Windows and Unix-based systems.
 *
 */
public class ClearDisplay {
    /**
     * Clears the console screen.
     */
    public static void ClearConsole() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
