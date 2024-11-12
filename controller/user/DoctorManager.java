package controller.user;

import java.util.List;
import java.util.Map;

import database.user.DoctorDatabase;
import model.user.Doctor;
import model.user.Patient;

public class DoctorManager {
    /**
     * Get all doctors
     * 
     * @return List of doctors
     */
    public static List<Doctor> getAllDoctors() {
        return DoctorDatabase.getDB().getAllDoctors();
    }

    /**
     * Get a random doctor ID
     * 
     * @return Random doctor ID
     */
    public static String getRandDoctorID() {
        List<Doctor> doctors = getAllDoctors();
        return doctors.get((int) (Math.random() * doctors.size())).getModelID();
    }

    /**
     * Get a doctor by their ID
     * 
     * @param doctorID
     * @return Doctor
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
     * Add allergy to patient
     * 
     * @param patient
     * @param newAllergy
     */
    public static void addPatientAllergies(Patient patient, String newAllergy) {
        try {
            PatientManager.addAllergy(patient, newAllergy);
        } catch (Exception e) {
            System.out.println("No allergies found.");
        }
    }

    /**
     * Set doctor's appointment availability
     * 
     * @param doctor
     * @return List of appointments
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
     * Get doctor's appointment availability
     * 
     * @param doctor
     * @return List of appointments
     */
    public static Map<String, List<String>> getAppointmentAvailability(Doctor doctor) {
        return doctor.getAppointmentAvailability();
    }

}
