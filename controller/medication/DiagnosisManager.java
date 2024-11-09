package controller.medication;

import java.util.ArrayList;
import java.util.List;

import database.medicalRecords.DiagnosisDatabase;
import model.diagnosis.Diagnosis;
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

}
