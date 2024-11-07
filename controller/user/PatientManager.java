package controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import database.user.PatientDatabase;
import model.diagnosis.Diagnosis;
import model.prescription.Prescription;
import model.user.Patient;
import utils.exceptions.ModelNotFoundException;

public class PatientManager {
    public static List<Patient> getAllPatients() {
        return PatientDatabase.getDB().getAllPatients();
    }

    public static List<Patient> getPatientsOfDoctor(String doctorId) {
        List<Patient> patients = new ArrayList<>();
        List<Patient> allPatients = getAllPatients();
        for (Patient patient : allPatients) {
            if (patient.getDoctorID().equals(doctorId))
                patients.add(patient);
        }
        return patients;
    }

    public static Patient getPatientById(String patientId) {
        try {
            return PatientDatabase.getDB().getByID(patientId);
        } catch (ModelNotFoundException e) {
            System.out.println("Patient not found");
            return null;
        }
    }

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

    public static void addDiagnosis(String diagnosis, String patientID, String doctorID, Prescription prescription) {
        String diagnosisID = UUID.randomUUID().toString();
        Date dateOfDiagnosis = new Date();
        try {
            Diagnosis newDiagnosis = new Diagnosis(diagnosisID, diagnosis, doctorID, prescription, dateOfDiagnosis);
            Patient patient = getPatientById(patientID);
            patient.addDiagnosis(newDiagnosis);
            UserManager.updateUser(patient);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

}
