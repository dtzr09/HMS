import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static void updateLoadedDoctor() {
        try {
            List<Doctor> doctors = DoctorDatabase.getDB().getAllDoctors();
            Map<String, List<String>> dummyMap = new HashMap<>();
            dummyMap.put("1", Arrays.asList("1", "2", "5"));
            dummyMap.put("2", Arrays.asList("12", "13", "14"));

            for (Doctor doctor : doctors) {
                if (doctor.getEmail().equals("john_smith@hotmail.com")) {
                    doctor.setAppointmentAvailability(dummyMap);
                    DoctorDatabase.getDB().update(doctor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start() {
        int flag = 0;
        if (isInitial()) {
            MedicationManager.loadMedication();
            AccountManager.loadHospitalStaffs();
            AccountManager.loadPatient();
            flag = 1;
        }
        if (flag == 1) {
            updateLoadedPatient();
            updateLoadedDoctor();
        }
        WelcomeDisplay.welcome();
    }

}
