import controller.account.AccountManager;
import controller.medication.MedicationManager;
import display.WelcomeDisplay;

public class InitialLoad {
    private static boolean isInitial() {
        return MedicationManager.isRepositoryEmpty();

    }

    public static void start() {
        if (isInitial()) {
            MedicationManager.loadMedication();
            AccountManager.loadHospitalStaffs();
        }
        WelcomeDisplay.welcome();
    }
}
