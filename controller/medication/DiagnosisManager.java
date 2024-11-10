package controller.medication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import database.medicalRecords.DiagnosisDatabase;
import model.diagnosis.Diagnosis;
import model.diagnosis.DiagnosisRecord;
import model.prescription.Prescription;
import model.user.Doctor;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;

public class DiagnosisManager {
    public static Diagnosis findDiagnosis(String diagnosisID) throws ModelNotFoundException {
        return DiagnosisDatabase.getDB().getByID(diagnosisID);
    }

    public static void addDiagnosis(Diagnosis diagnosis) throws ModelAlreadyExistsException {
        DiagnosisDatabase.getDB().add(diagnosis);
    }

    public static void updateDiagnsosis(Diagnosis diagnosis) throws ModelNotFoundException {
        DiagnosisDatabase.getDB().update(diagnosis);
    }

    public static boolean isRepositoryEmpty() {
        return DiagnosisDatabase.getDB().isEmpty();
    }

    public static List<Diagnosis> getAllDiagnosis() {
        return DiagnosisDatabase.getDB().getAllDiagnosis();
    }

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

    public static void createNewDiagnosis(String diagnosis, String patientID, String doctorID, String prescriptionID) {
        String diagnosisID = UUID.randomUUID().toString();
        Date dateOfDiagnosis = new Date();
        try {
            Diagnosis newDiagnosis = new Diagnosis(diagnosisID, diagnosis, doctorID, prescriptionID, dateOfDiagnosis,
                    patientID);
            addDiagnosis(newDiagnosis);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

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
}
