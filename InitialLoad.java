import java.util.List;

import controller.account.AccountManager;
import controller.medication.MedicationManager;
import controller.user.PatientManager;
import database.user.DoctorDatabase;
import database.user.PatientDatabase;
import display.session.WelcomeDisplay;
import model.user.Doctor;
import model.user.Patient;

public class InitialLoad {
    private static boolean isInitial() {
        return MedicationManager.isRepositoryEmpty() || PatientManager.isRepositoryEmpty()
                || AccountManager.isHospitalStaffsRepositoryEmpty();
    }

    private static void updateLoadedPatient() {
        try {
            List<Patient> patients = PatientDatabase.getDB().getAllPatients();
            List<Doctor> doctors = DoctorDatabase.getDB().getAllDoctors();
            String doctorID = doctors.get(0).getModelID();
            for (Patient patient : patients) {
                if (patient.getEmail().equals("alice_brown@hotmail.com")) {
                    patient.setDoctorID(doctorID);
                    PatientDatabase.getDB().update(patient);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start() {
        int flag = 0;
        if (isInitial()) {
            try {
                MedicationManager.loadMedication();
                AccountManager.loadHospitalStaffs();
                AccountManager.loadPatient();
            } catch (Exception e) {
                e.printStackTrace();
            }
            flag = 1;
        }
        if (flag == 1) {
            updateLoadedPatient();
        }
        WelcomeDisplay.welcome();
    }

}
