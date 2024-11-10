package controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import controller.medication.DiagnosisManager;
import database.user.PatientDatabase;
import model.diagnosis.Diagnosis;
import model.user.Patient;
import utils.exceptions.ModelNotFoundException;

public class PatientManager {
    public static List<Patient> getAllPatients() {
        return PatientDatabase.getDB().getAllPatients();
    }

    public static boolean isRepositoryEmpty() {
        return PatientDatabase.getDB().isEmpty();
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

    public static Diagnosis getDiagnosisByID(Patient patient, String diagnosisID) {
        try {
            List<Diagnosis> diagnoses = DiagnosisManager.getDiagnosisByPatientID(patient.getPatientID());
            Diagnosis diagnosis = null;

            for (Diagnosis d : diagnoses) {
                if (d.getDiagnosisID().equals(diagnosisID)) {
                    diagnosis = d;
                    break;
                }
            }

            if (diagnosis == null) {
                throw new ModelNotFoundException();
            }

            return diagnosis;
        } catch (ModelNotFoundException e) {
            System.out.println("Diagnosis not found.");
        }
        return null;
    }

    public static void updateDisease(String newDisease, String patientID, String diagnosisID) {
        try {
            Patient patient = getPatientById(patientID);
            List<Diagnosis> diagnoses = DiagnosisManager.getDiagnosisByPatientID(patientID);
            for (Diagnosis diagnosis : diagnoses) {
                if (diagnosis.getDiagnosisID().equals(diagnosisID)) {
                    diagnosis.setDisease(newDisease);
                    break;
                }
            }
            UserManager.updateUser(patient);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}
