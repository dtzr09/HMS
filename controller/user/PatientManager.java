package controller.user;

import java.util.ArrayList;
import java.util.List;

import database.user.PatientDatabase;
import model.user.Patient;
import utils.exceptions.ModelNotFoundException;
/**
 * The PatientManager class provides utility methods for handling the functionalities of a Patient.
 */

public class PatientManager {
    /**
     * Retrieves all patients from the patient database.
     * 
     * This method returns a list of all patients in the system by fetching them from the `PatientDatabase`.
     * 
     * @return a list of all patients in the database.
     */
    public static List<Patient> getAllPatients() {
        return PatientDatabase.getDB().getAllPatients();
    }

    /**
     * Checks if the patient database is empty.
     * 
     * This method checks whether the patient database contains any patients. It returns `true` if the 
     * database is empty, and `false` otherwise.
     * 
     * @return `true` if the patient database is empty, `false` otherwise.
     */
    public static boolean isRepositoryEmpty() {
        return PatientDatabase.getDB().isEmpty();
    }

    /**
     * Retrieves all patients associated with a specific doctor.
     * 
     * This method filters all patients by their associated doctor ID and returns a list of patients 
     * who are assigned to the given doctor.
     * 
     * @param doctorId the unique ID of the doctor whose patients are to be retrieved.
     * @return a list of patients associated with the specified doctor.
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
     * Retrieves a patient by their unique patient ID.
     * 
     * This method fetches a patient from the `PatientDatabase` using the provided patient ID. 
     * If the patient is not found, an error message is printed, and the method returns `null`.
     * 
     * @param patientId the unique ID of the patient to be retrieved.
     * @return the patient with the specified ID, or `null` if the patient is not found.
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
     * Adds an allergy to a patient's list of allergies.
     * 
     * This method adds a new allergy to the patient's list of allergies and updates the patient record
     * in the `PatientDatabase`. If any error occurs, an error message is printed.
     * 
     * @param patient the patient to whom the allergy will be added.
     * @param allergy the new allergy to be added.
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
