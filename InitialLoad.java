import controller.account.MedicationManager;
import display.WelcomeDisplay;

public class InitialLoad {
    // private static boolean isInitial() {
    // return MedicationManager.isRepositoryEmpty();
    // }

    public static void start() {
        // if (isInitial()) {
        // MedicationManager.loadMedication();
        // }
        WelcomeDisplay.welcome();
    }
}
