package model.diagnosis;

import java.util.Map;

import model.Model;

public class Diagnosis implements Model {
    private String patientID;
    private String diagnosisID;
    private String disease;
    private String doctorID;
    private String prescriptionID;
    private String dateOfDiagnosis;

    /**
     * Default constructor for Diagnosis.
     */
    public Diagnosis() {
    }

    /**
     * Constructs a Diagnosis with specified fields.
     *
     * @param diagnosisID     the unique identifier for the diagnosis.
     * @param disease         the name of the diagnosed disease.
     * @param doctorID        the unique identifier for the doctor who made the
     *                        diagnosis.
     * @param prescriptionID  the unique identifier for the prescription associated
     *                        with the diagnosis.
     * @param dateOfDiagnosis the date the diagnosis was made.
     * @param patientID       the unique identifier for the patient.
     */
    public Diagnosis(String diagnosisID, String disease, String doctorID, String prescriptionID,
            String dateOfDiagnosis, String patientID) {
        this.diagnosisID = diagnosisID;
        this.disease = disease;
        this.doctorID = doctorID;
        this.prescriptionID = prescriptionID;
        this.dateOfDiagnosis = dateOfDiagnosis;
        this.patientID = patientID;
    }

    /**
     * Constructs a Diagnosis by converting a map of attributes.
     *
     * @param map a map of diagnosis attributes.
     */
    public Diagnosis(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the model ID.
     *
     * @return the unique identifier for the diagnosis.
     */
    public String getModelID() {
        return diagnosisID;
    }

    /**
     * Retrieves the patient ID associated with the diagnosis.
     *
     * @return the unique identifier for the patient.
     */
    public String getPatientID() {
        return patientID;
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
     * Retrieves the prescription ID associated with the diagnosis.
     *
     * @return the unique identifier for the prescription.
     */
    public String getPrescriptionID() {
        return prescriptionID;
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
     * @param dateOfDiagnosis the date to set for the diagnosis.
     */
    public void setDateOfDiagnosis(String dateOfDiagnosis) {
        this.dateOfDiagnosis = dateOfDiagnosis;
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
     * Retrieves the doctor ID associated with the diagnosis.
     *
     * @return the unique identifier for the doctor.
     */
    public String getDoctorID() {
        return doctorID;
    }

    /**
     * Sets the doctor ID associated with the diagnosis.
     *
     * @param doctorID the doctor ID to set.
     */
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * Sets the prescription ID associated with the diagnosis.
     *
     * @param prescriptionID the prescription ID to set.
     */
    public void setPrescription(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    /**
     * Sets the patient ID associated with the diagnosis.
     *
     * @param patientID the patient ID to set.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

}
