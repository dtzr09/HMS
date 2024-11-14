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
import model.prescription.enums.PrescriptionStatus;
import model.user.Doctor;
import model.user.Patient;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.utils.FormatDateTime;

/**
 * The DiagnosisManager class provides utility methods for handing Diagnosis
 * such as retieving, updating and adding it.
 */

public class DiagnosisManager {

    /**
     * Finds a diagnosis by its ID.
     * 
     * This method retrieves a diagnosis from the database using the provided
     * diagnosis ID. If no diagnosis with the given ID
     * exists, a {@link ModelNotFoundException} is thrown.
     * 
     * @param diagnosisID The ID of the diagnosis to be retrieved.
     * @return The diagnosis object associated with the given ID.
     * @throws ModelNotFoundException if no diagnosis is found with the provided ID.
     */
    public static Diagnosis findDiagnosis(String diagnosisID) throws ModelNotFoundException {
        return DiagnosisDatabase.getDB().getByID(diagnosisID);
    }

    /**
     * Adds a new diagnosis to the database.
     * 
     * This method adds a new diagnosis to the database. If a diagnosis with the
     * same ID already exists, a
     * {@link ModelAlreadyExistsException} is thrown.
     * 
     * @param diagnosis The diagnosis to be added.
     * @throws ModelAlreadyExistsException if a diagnosis with the same ID already
     *                                     exists in the database.
     */
    public static void addDiagnosis(Diagnosis diagnosis) throws ModelAlreadyExistsException {
        DiagnosisDatabase.getDB().add(diagnosis);
    }

    /**
     * Updates an existing diagnosis in the database.
     * 
     * This method updates the details of an existing diagnosis. If the diagnosis to
     * be updated is not found in the database,
     * a {@link ModelNotFoundException} is thrown.
     * 
     * @param diagnosis The diagnosis with updated details.
     * @throws ModelNotFoundException if the diagnosis to be updated does not exist
     *                                in the database.
     */
    public static void updateDiagnsosis(Diagnosis diagnosis) throws ModelNotFoundException {
        DiagnosisDatabase.getDB().update(diagnosis);
    }

    /**
     * Checks if the diagnosis repository is empty.
     * 
     * This method checks if the diagnosis repository (database) has any records. It
     * returns true if the repository is empty,
     * and false otherwise.
     * 
     * @return true if the diagnosis repository is empty, false otherwise.
     */
    public static boolean isRepositoryEmpty() {
        return DiagnosisDatabase.getDB().isEmpty();
    }

    /**
     * Retrieves all diagnoses from the database.
     * 
     * This method fetches and returns a list of all diagnoses stored in the
     * database.
     * 
     * @return A list of all diagnoses.
     */
    public static List<Diagnosis> getAllDiagnosis() {
        return DiagnosisDatabase.getDB().getAllDiagnosis();
    }

    /**
     * Retrieves a list of diagnoses associated with a specific patient ID.
     * 
     * This method searches through all diagnoses and returns a list of diagnoses
     * that are linked to the provided patient ID.
     * If no diagnoses are found for the patient, it returns an empty list.
     * 
     * @param patientID The ID of the patient whose diagnoses are to be retrieved.
     * @return A list of diagnoses associated with the given patient ID, or an empty
     *         list if no diagnoses are found.
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

    public static List<Diagnosis> getDiagnosisByPatientIDAndDiagnosisDetails(String patientID)
            throws ModelNotFoundException {
        ArrayList<Diagnosis> patientDiagnosis = new ArrayList<>();
        try {
            List<Diagnosis> diagnoses = getAllDiagnosis();
            for (Diagnosis d : diagnoses) {
                Prescription prescription = PrescriptionManager.getPrescriptionByID(d.getPrescriptionID());
                if (d.getPatientID().equals(patientID) && !prescription.getMedicationIDs().isEmpty()
                        && prescription.getPrescriptionStatus() == PrescriptionStatus.PENDING) {
                    patientDiagnosis.add(d);
                }
            }
            return patientDiagnosis;
        } catch (NullPointerException e) {
            return patientDiagnosis;
        }
    }

    /**
     * Retrieves a specific diagnosis for a patient based on the provided patient ID
     * and diagnosis ID.
     * 
     * This method searches through all diagnoses associated with a specific patient
     * and returns the diagnosis
     * that matches the provided diagnosis ID. If no matching diagnosis is found, it
     * returns null.
     * 
     * @param patientID   The ID of the patient whose diagnosis is to be retrieved.
     * @param diagnosisID The ID of the diagnosis to be retrieved.
     * @return The diagnosis associated with the given patient ID and diagnosis ID,
     *         or null if no match is found.
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
     * Creates a new diagnosis entry and adds it to the diagnosis database.
     * 
     * This method generates a new diagnosis ID, retrieves the current date for the
     * diagnosis date, and
     * creates a new Diagnosis object with the provided diagnosis details. The new
     * diagnosis is then added
     * to the database using the `addDiagnosis` method.
     * 
     * @param diagnosis      The diagnosis description or name.
     * @param patientID      The ID of the patient for whom the diagnosis is being
     *                       created.
     * @param doctorID       The ID of the doctor making the diagnosis.
     * @param prescriptionID The ID of the prescription related to the diagnosis.
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
     * Retrieves a list of diagnosis outcome records for the specified diagnoses and
     * doctor.
     * 
     * This method iterates over the list of diagnoses, retrieves the associated
     * prescription details,
     * and extracts the medication names. It then creates a `DiagnosisRecord` for
     * each diagnosis, containing
     * the diagnosis ID, disease description, doctor's name, medication names, and
     * the diagnosis date.
     * The list of diagnosis records is returned.
     * 
     * @param diagnoses A list of `Diagnosis` objects to retrieve the outcome
     *                  records for.
     * @param doctor    The `Doctor` object who made the diagnoses and whose name
     *                  will be included in the record.
     * @return A list of `DiagnosisRecord` objects, each containing details about a
     *         diagnosis outcome.
     * @throws ModelNotFoundException If any related model (prescription,
     *                                medication, etc.) cannot be found.
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
     * Retrieves a single diagnosis record for a patient based on the provided
     * diagnosis and doctor.
     * 
     * This method retrieves the prescription associated with the given diagnosis,
     * extracts the medication names,
     * and creates a `DiagnosisRecord` containing the diagnosis ID, disease
     * description, doctor's name,
     * medication names, and the date of diagnosis. The created `DiagnosisRecord`
     * object is then returned.
     * 
     * @param diagnosis The `Diagnosis` object for which the record is being
     *                  created.
     * @param doctor    The `Doctor` object who made the diagnosis and whose name
     *                  will be included in the record.
     * @return A `DiagnosisRecord` object containing the diagnosis details,
     *         medication information, and doctor's name.
     * @throws ModelNotFoundException If the prescription or medication associated
     *                                with the diagnosis cannot be found.
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
     * Retrieves a specific diagnosis for a patient by diagnosis ID.
     * 
     * This method searches through a patient's list of diagnoses and returns the
     * one that matches the provided
     * diagnosis ID. If the diagnosis is not found, a `ModelNotFoundException` is
     * thrown.
     * 
     * @param patient     The `Patient` object whose diagnoses are being searched.
     * @param diagnosisID The ID of the diagnosis to be retrieved.
     * @return The `Diagnosis` object matching the provided diagnosis ID, or `null`
     *         if not found.
     */
    public static Diagnosis getDiagnosisByID(Patient patient, String diagnosisID) throws ModelNotFoundException {
        List<Diagnosis> diagnoses = DiagnosisManager.getDiagnosisByPatientID(patient.getPatientID());

        for (Diagnosis d : diagnoses) {
            if (d.getDiagnosisID().equals(diagnosisID)) {
                return d;
            }
        }

        throw new ModelNotFoundException();
    }

    /**
     * Updates the disease information for a patient's diagnosis.
     * 
     * This method allows for updating the disease associated with a specific
     * diagnosis of a patient. It searches
     * for the diagnosis by its ID, and if found, updates the disease information.
     * After the update, the patient record
     * is saved using the `UserManager.updateUser()` method.
     * 
     * @param newDisease  The new disease name to be set in the diagnosis.
     * @param patientID   The ID of the patient whose diagnosis needs to be updated.
     * @param diagnosisID The ID of the diagnosis to be updated.
     * @throws ModelNotFoundException If the patient or diagnosis cannot be found.
     */
    public static void updateDisease(String newDisease, String patientID, String diagnosisID)
            throws ModelNotFoundException {
        Patient patient = PatientManager.getPatientById(patientID);
        List<Diagnosis> diagnoses = DiagnosisManager.getDiagnosisByPatientID(patientID);
        for (Diagnosis diagnosis : diagnoses) {
            if (diagnosis.getDiagnosisID().equals(diagnosisID)) {
                diagnosis.setDisease(newDisease);
                break;
            }
        }
        UserManager.updateUser(patient);

    }
}
