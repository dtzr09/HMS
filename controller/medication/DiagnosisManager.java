package controller.medication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import controller.user.PatientManager;
import controller.user.UserManager;
import database.medicalRecords.DiagnosisDatabase;
import model.diagnosis.Diagnosis;
import model.diagnosis.DiagnosisRecord;
import model.prescription.Prescription;
import model.user.Doctor;
import model.user.Patient;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.utils.FormatDateTime;

public class DiagnosisManager {

    /**
     * Find a diagnosis by its ID
     * 
     * @param diagnosisID
     * @return Diagnosis
     * @throws ModelNotFoundException
     */
    public static Diagnosis findDiagnosis(String diagnosisID) throws ModelNotFoundException {
        return DiagnosisDatabase.getDB().getByID(diagnosisID);
    }

    /**
     * Add a diagnosis to the database
     * 
     * @param diagnosis
     * @throws ModelAlreadyExistsException
     */
    public static void addDiagnosis(Diagnosis diagnosis) throws ModelAlreadyExistsException {
        DiagnosisDatabase.getDB().add(diagnosis);
    }

    /**
     * Delete a diagnosis from the database
     * 
     * @param diagnosis
     * @throws ModelNotFoundException
     */
    public static void updateDiagnsosis(Diagnosis diagnosis) throws ModelNotFoundException {
        DiagnosisDatabase.getDB().update(diagnosis);
    }

    /**
     * Delete a diagnosis from the database
     * 
     * @return true if the diagnosis database is empty, false otherwise
     */
    public static boolean isRepositoryEmpty() {
        return DiagnosisDatabase.getDB().isEmpty();
    }

    /**
     * Get all diagnoses from the database
     * 
     * @return List of Diagnosis
     */
    public static List<Diagnosis> getAllDiagnosis() {
        return DiagnosisDatabase.getDB().getAllDiagnosis();
    }

    /**
     * Get all diagnoses of a patient
     * 
     * @param patientID
     * @return List of Diagnosis
     */
    public static List<Diagnosis> getDiagnosisByPatientID(String patientID) {
        ArrayList<Diagnosis> patientDiagnosis = new ArrayList<>();
        try {
            List<Diagnosis> diagnoses = getAllDiagnosis();
            for (Diagnosis d : diagnoses) {
                if (d.getPatientID().equals(patientID)) {
                    patientDiagnosis.add(d);
                }
            }
            return patientDiagnosis;
        } catch (NullPointerException e) {
            return patientDiagnosis;
        }
    }

    /**
     * Get a diagnosis by patient ID and diagnosis ID
     * 
     * @param patientID
     * @param diagnosisID
     * @return Diagnosis
     */
    public static Diagnosis getDiagnosisByPatientIDAndDiagnosisID(String patientID, String diagnosisID) {
        try {
            List<Diagnosis> diagnoses = getDiagnosisByPatientID(patientID);
            for (Diagnosis d : diagnoses) {
                if (d.getDiagnosisID().equals(diagnosisID)) {
                    return d;
                }
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Create a new diagnosis
     * 
     * @param diagnosis
     * @param patientID
     * @param doctorID
     * @param prescriptionID
     */
    public static void createNewDiagnosis(String diagnosis, String patientID, String doctorID, String prescriptionID) {
        String diagnosisID = UUID.randomUUID().toString();
        Date dateOfDiagnosis = new Date();
        String dateOfDiagnosisStr = FormatDateTime.toDateOnly(dateOfDiagnosis);
        try {
            Diagnosis newDiagnosis = new Diagnosis(diagnosisID, diagnosis, doctorID, prescriptionID, dateOfDiagnosisStr,
                    patientID);
            addDiagnosis(newDiagnosis);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    /**
     * Get a list of diagnosis records
     * 
     * @param diagnoses
     * @param doctor
     * @return List of DiagnosisRecord
     * @throws ModelNotFoundException
     */
    public static List<DiagnosisRecord> getDiagnosisOutcomeRecordList(List<Diagnosis> diagnoses, Doctor doctor)
            throws ModelNotFoundException {
        List<DiagnosisRecord> diagnosisRecords = new ArrayList<>();
        for (Diagnosis diagnosis : diagnoses) {
            Prescription prescription = PrescriptionManager.getPrescriptionByID(diagnosis.getPrescriptionID());
            ArrayList<String> medicationNames = MedicationManager
                    .getListOfMedicationNamesByIDs(prescription.getMedicationIDs());
            DiagnosisRecord diagnosisRecord = new DiagnosisRecord(diagnosis.getDiagnosisID(), diagnosis.getDisease(),
                    doctor.getName(),
                    medicationNames, diagnosis.getDateOfDiagnosis());
            diagnosisRecords.add(diagnosisRecord);
        }
        return diagnosisRecords;
    }

    /**
     * Get a patient diagnosis record
     * 
     * @param diagnosis
     * @param doctor
     * @return DiagnosisRecord
     * @throws ModelNotFoundException
     */
    public static DiagnosisRecord getAPatientDiagnosisRecord(Diagnosis diagnosis, Doctor doctor)
            throws ModelNotFoundException {
        Prescription prescription = PrescriptionManager.getPrescriptionByID(diagnosis.getPrescriptionID());
        ArrayList<String> medicationNames = MedicationManager
                .getListOfMedicationNamesByIDs(prescription.getMedicationIDs());
        DiagnosisRecord diagnosisRecord = new DiagnosisRecord(diagnosis.getDiagnosisID(), diagnosis.getDisease(),
                doctor.getName(),
                medicationNames, diagnosis.getDateOfDiagnosis());
        return diagnosisRecord;
    }

    /**
     * Get diagnosis by patient and diagnosis ID
     * 
     * @param patient
     * @param diagnosisID
     * @return
     */
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

    /**
     * Update disease
     * 
     * @param newDisease
     * @param patientID
     * @param diagnosisID
     */
    public static void updateDisease(String newDisease, String patientID, String diagnosisID) {
        try {
            Patient patient = PatientManager.getPatientById(patientID);
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
