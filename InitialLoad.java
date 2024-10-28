import controller.account.MedicationManager;
import display.LoginDisplay;

public class InitialLoad {
    private static boolean isInitial() {
        return MedicationManager.isRepositoryEmpty();
    }

    public static void start() {
        if (isInitial()) {
            MedicationManager.loadMedication();
        }
        LoginDisplay.loginDisplay();
    }
}
