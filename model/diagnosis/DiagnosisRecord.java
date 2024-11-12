package model.diagnosis;

import java.util.ArrayList;

/**
 * This class represents a record of a diagnosis for a patient, including
 * details about the diagnosed disease, the doctor, prescribed medications,
 * and the date of diagnosis.
 */
public class DiagnosisRecord {
    private String diagnosisID;
    private String disease;
    private String doctorName;
    private ArrayList<String> medicationNames;
    private String dateOfDiagnosis;

    /**
     * Constructs a DiagnosisRecord with all specified fields.
     *
     * @param diagnosisID     the unique identifier for the diagnosis.
     * @param disease         the name of the diagnosed disease.
     * @param doctorName      the name of the doctor who made the diagnosis.
     * @param medicationNames a list of medication names prescribed.
     * @param dateOfDiagnosis the date the diagnosis was made.
     */
    public DiagnosisRecord(String diagnosisID, String disease, String doctorName, ArrayList<String> medicationNames,
            String dateOfDiagnosis) {
        this.diagnosisID = diagnosisID;
        this.disease = disease;
        this.doctorName = doctorName;
        this.medicationNames = medicationNames;
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    /**
     * Retrieves the diagnosis ID.
     *
     * @return the unique identifier for the diagnosis.
     */
    public String getDiagnosisID() {
        return diagnosisID;
    }

    /**
     * Sets the diagnosis ID.
     *
     * @param diagnosisID the diagnosis ID to set.
     */
    public void setDiagnosisID(String diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    /**
     * Retrieves the disease associated with the diagnosis.
     *
     * @return the name of the disease.
     */
    public String getDisease() {
        return disease;
    }

    /**
     * Sets the disease associated with the diagnosis.
     *
     * @param disease the disease name to set.
     */
    public void setDisease(String disease) {
        this.disease = disease;
    }

    /**
     * Retrieves the name of the doctor who made the diagnosis.
     *
     * @return the doctor's name.
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Sets the name of the doctor who made the diagnosis.
     *
     * @param doctorName the doctor's name to set.
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * Retrieves the list of medication names prescribed.
     *
     * @return the list of medication names.
     */
    public ArrayList<String> getMedicationNames() {
        return medicationNames;
    }

    /**
     * Sets the list of medication names prescribed.
     *
     * @param medicationNames the list of medication names to set.
     */
    public void setMedicationNames(ArrayList<String> medicationNames) {
        this.medicationNames = medicationNames;
    }

    /**
     * Retrieves the date of the diagnosis.
     *
     * @return the date the diagnosis was made.
     */
    public String getDateOfDiagnosis() {
        return dateOfDiagnosis;
    }

    /**
     * Sets the date of the diagnosis.
     *
     * @param dateOfDiagnosis the date of the diagnosis to set.
     */
    public void setDateOfDiagnosis(String dateOfDiagnosis) {
        this.dateOfDiagnosis = dateOfDiagnosis;
    }
}
