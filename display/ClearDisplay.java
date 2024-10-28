package display;

public class ClearDisplay {
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
        // } catch (IOException | InterruptedException e) {
        // e.printStackTrace();
        // }
    }
}
