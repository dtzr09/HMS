package controller.user;

import java.util.List;
import java.util.Map;

import database.user.DoctorDatabase;
import model.user.Doctor;
import model.user.Patient;
/**
 * The DoctorManager class provides utility methods for handling the functionalities of a Doctor.
 */

public class DoctorManager {
    /**
     * Retrieves the list of all doctors in the system.
     * 
     * This method returns a list of all doctors from the `DoctorDatabase`.
     * 
     * @return a list of all doctors.
     */
    public static List<Doctor> getAllDoctors() {
        return DoctorDatabase.getDB().getAllDoctors();
    }

    /**
     * Retrieves a random doctor's ID from the list of all doctors.
     * 
     * This method selects a random doctor from the list of all doctors and returns their ID.
     * 
     * @return the ID of a randomly selected doctor.
     */
    public static String getRandDoctorID() {
        List<Doctor> doctors = getAllDoctors();
        return doctors.get((int) (Math.random() * doctors.size())).getModelID();
    }

    /**
     * Retrieves a doctor by their unique doctor ID.
     * 
     * This method fetches a doctor from the `DoctorDatabase` using the provided doctor ID.
     * If the doctor is not found, an error message is printed.
     * 
     * @param doctorID the unique ID of the doctor.
     * @return the doctor with the specified ID, or null if the doctor is not found.
     */
    public static Doctor getDoctorByID(String doctorID) {
        try {
            return DoctorDatabase.getDB().getByID(doctorID);
        } catch (Exception e) {
            System.out.println("Doctor not found.");
        }
        return null;
    };

    /**
     * Adds a new allergy to the patient's list of allergies.
     * 
     * This method adds a new allergy to the patient's existing list of allergies. If no allergies
     * are found for the patient, an error message is printed.
     * 
     * @param patient the patient to whom the allergy will be added.
     * @param newAllergy the new allergy to be added to the patient's list.
     */
    public static void addPatientAllergies(Patient patient, String newAllergy) {
        try {
            PatientManager.addAllergy(patient, newAllergy);
        } catch (Exception e) {
            System.out.println("No allergies found.");
        }
    }

    /**
     * Sets the appointment availability for a specific doctor.
     * 
     * This method sets the appointment availability for the specified doctor by updating their 
     * availability using the provided `appointmentAvailability` map.
     * 
     * @param doctor the doctor whose availability will be updated.
     * @param appointmentAvailability a map representing the availability schedule.
     */
    public static void setAppointmentAvailability(Doctor doctor, Map<String, List<String>> appointmentAvailability) {
        try {
            System.out.println("Set" + appointmentAvailability);
            doctor.setAppointmentAvailability(appointmentAvailability);
            UserManager.updateUser(doctor);
        } catch (Exception e) {
            System.out.println("Error updating appointment availability.");
        }
    }

    /**
     * Retrieves the appointment availability for a specific doctor.
     * 
     * This method retrieves the current appointment availability schedule for the specified doctor.
     * 
     * @param doctor the doctor whose appointment availability is being requested.
     * @return the doctor's appointment availability schedule.
     */
    public static Map<String, List<String>> getAppointmentAvailability(Doctor doctor) {
        return doctor.getAppointmentAvailability();
    }

}
