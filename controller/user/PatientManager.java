package controller.user;

import java.util.ArrayList;
import java.util.List;

import database.user.PatientDatabase;
import model.user.Patient;
import utils.exceptions.ModelNotFoundException;

public class PatientManager {
    /**
     * Get all patients
     * 
     * @return List of patients
     */
    public static List<Patient> getAllPatients() {
        return PatientDatabase.getDB().getAllPatients();
    }

    /**
     * Check if patient database is empty
     * 
     * @return True if empty, false otherwise
     */
    public static boolean isRepositoryEmpty() {
        return PatientDatabase.getDB().isEmpty();
    }

    /**
     * Get all patients of a doctor
     * 
     * @param doctorId
     * @return List of patients
     */
    public static List<Patient> getPatientsOfDoctor(String doctorId) {
        List<Patient> patients = new ArrayList<>();
        List<Patient> allPatients = getAllPatients();
        for (Patient patient : allPatients) {
            if (patient.getDoctorID().equals(doctorId))
                patients.add(patient);
        }
        return patients;
    }

    /**
     * Get patient by ID
     * 
     * @param patientId
     * @return Patient
     */
    public static Patient getPatientById(String patientId) {
        try {
            return PatientDatabase.getDB().getByID(patientId);
        } catch (ModelNotFoundException e) {
            System.out.println("Patient not found");
            return null;
        }
    }

    /**
     * Add allergy to patient
     * 
     * @param patient
     * @param allergy
     */
    public static void addAllergy(Patient patient, String allergy) {
        try {
            ArrayList<String> allergies = patient.getAllergies();
            allergies.add(allergy);
            patient.setAllergies(allergies);
            PatientDatabase.getDB().update(patient);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

}
