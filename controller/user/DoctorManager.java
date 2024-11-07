package controller.user;

import database.user.DoctorDatabase;
import model.user.Doctor;
import model.user.Patient;

public class DoctorManager {
    public static Doctor getDoctorByID(String doctorID) {
        try {
            return DoctorDatabase.getDB().getByID(doctorID);
        } catch (Exception e) {
            System.out.println("Doctor not found.");
        }
        return null;
    };

    public static void addPatientAllergies(Patient patient, String newAllergy) {
        try {
            PatientManager.addAllergy(patient, newAllergy);
        } catch (Exception e) {
            System.out.println("No allergies found.");
        }
    }

}
