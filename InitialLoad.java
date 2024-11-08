import controller.account.AccountManager;
import controller.medication.MedicationManager;
import controller.user.PatientManager;
import display.WelcomeDisplay;

public class InitialLoad {
    private static boolean isInitial() {
        return MedicationManager.isRepositoryEmpty() || PatientManager.isRepositoryEmpty()
                || AccountManager.isHospitalStaffsRepositoryEmpty();
    }

    public static void start() {
        if (isInitial()) {
            MedicationManager.loadMedication();
            AccountManager.loadHospitalStaffs();
            AccountManager.loadPatient();
        }
        WelcomeDisplay.welcome();
    }
}
