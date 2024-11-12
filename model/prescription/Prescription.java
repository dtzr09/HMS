package model.prescription;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import model.Model;
import model.prescription.enums.PrescriptionStatus;

/**
 * Represents a prescription issued by a doctor for a patient.
 * Contains details about the medications, issuing doctor, assigned pharmacist,
 * date of prescription, and specific drug instructions.
 */
public class Prescription implements Model {
    private String prescriptionID;
    private String patientID;
    private String pharmacistID;
    private String doctorID;
    private ArrayList<String> medicationIDs;
    private Date dateOfPrescription;
    private String drugInstructions;
    private PrescriptionStatus prescriptionStatus;

    /**
     * Default constructor for Prescription.
     */
    public Prescription() {
    }

    /**
     * Constructs a Prescription with all specified fields.
     *
     * @param prescriptionID     the unique identifier for the prescription.
     * @param patientID          the unique identifier for the patient.
     * @param pharmacistID       the unique identifier for the pharmacist.
     * @param doctorID           the unique identifier for the doctor who issued the
     *                           prescription.
     * @param medicationIDs      a list of medication IDs included in the
     *                           prescription.
     * @param dateOfPrescription the date the prescription was issued.
     * @param drugInstructions   the instructions for drug usage.
     * @param prescriptionStatus the status of the prescription.
     */
    public Prescription(String prescriptionID, String patientID, String pharmacistID, String doctorID,
            ArrayList<String> medicationIDs, Date dateOfPrescription, String drugInstructions,
            PrescriptionStatus prescriptionStatus) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.pharmacistID = pharmacistID;
        this.doctorID = doctorID;
        this.medicationIDs = medicationIDs;
        this.dateOfPrescription = dateOfPrescription;
        this.drugInstructions = drugInstructions;
        this.prescriptionStatus = prescriptionStatus;
    }

    /**
     * Constructs a Prescription without a pharmacist ID.
     *
     * @param prescriptionID     the unique identifier for the prescription.
     * @param patientID          the unique identifier for the patient.
     * @param doctorID           the unique identifier for the doctor who issued the
     *                           prescription.
     * @param medicationIDs      a list of medication IDs included in the
     *                           prescription.
     * @param dateOfPrescription the date the prescription was issued.
     * @param drugInstructions   the instructions for drug usage.
     * @param prescriptionStatus the status of the prescription.
     */
    public Prescription(String prescriptionID, String patientID, String doctorID,
            ArrayList<String> medicationIDs, Date dateOfPrescription, String drugInstructions,
            PrescriptionStatus prescriptionStatus) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medicationIDs = medicationIDs;
        this.dateOfPrescription = dateOfPrescription;
        this.drugInstructions = drugInstructions;
        this.prescriptionStatus = prescriptionStatus;
    }

    /**
     * Constructs a Prescription by converting a map of attributes.
     *
     * @param map a map of prescription attributes.
     */
    public Prescription(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the unique identifier for the prescription.
     *
     * @return the prescription ID.
     */
    public String getModelID() {
        return prescriptionID;
    }

    /**
     * Retrieves the prescription ID.
     *
     * @return the unique identifier for the prescription.
     */
    public String getPrescriptionID() {
        return prescriptionID;
    }

    /**
     * Sets the prescription ID.
     *
     * @param prescriptionID the prescription ID to set.
     */
    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    /**
     * Retrieves the patient ID associated with the prescription.
     *
     * @return the patient ID.
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Sets the patient ID associated with the prescription.
     *
     * @param patientID the patient ID to set.
     */
    public void setPatient(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Retrieves the pharmacist ID associated with the prescription.
     *
     * @return the pharmacist ID.
     */
    public String getPharmacist() {
        return pharmacistID;
    }

    /**
     * Sets the pharmacist ID associated with the prescription.
     *
     * @param pharmacistID the pharmacist ID to set.
     */
    public void setPharmacist(String pharmacistID) {
        this.pharmacistID = pharmacistID;
    }

    /**
     * Retrieves the doctor ID associated with the prescription.
     *
     * @return the doctor ID.
     */
    public String getdoctorID() {
        return doctorID;
    }

    /**
     * Sets the doctor ID associated with the prescription.
     *
     * @param doctorID the doctor ID to set.
     */
    public void setdoctor(String doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * Retrieves the list of medication IDs included in the prescription.
     *
     * @return the list of medication IDs.
     */
    public ArrayList<String> getMedicationIDs() {
        return medicationIDs;
    }

    /**
     * Sets the list of medication IDs included in the prescription.
     *
     * @param medicationIDs the list of medication IDs to set.
     */
    public void setMedicationIDs(ArrayList<String> medicationIDs) {
        this.medicationIDs = medicationIDs;
    }

    /**
     * Retrieves the date the prescription was issued.
     *
     * @return the date of the prescription.
     */
    public Date getDateOfPrescription() {
        return dateOfPrescription;
    }

    /**
     * Sets the date the prescription was issued.
     *
     * @param dateOfPrescription the date of the prescription to set.
     */
    public void setDateOfPrescription(Date dateOfPrescription) {
        this.dateOfPrescription = dateOfPrescription;
    }

    /**
     * Retrieves the drug usage instructions.
     *
     * @return the drug instructions.
     */
    public String getDrugInstructions() {
        return drugInstructions;
    }

    /**
     * Sets the drug usage instructions.
     *
     * @param drugInstructions the drug instructions to set.
     */
    public void setDrugInstructions(String drugInstructions) {
        this.drugInstructions = drugInstructions;
    }

    /**
     * Retrieves the status of the prescription.
     *
     * @return the prescription status.
     */
    public PrescriptionStatus getPrescriptionStatus() {
        return this.prescriptionStatus;
    }

    /**
     * Sets the status of the prescription.
     *
     * @param prescriptionStatus the prescription status to set.
     */
    public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }
}
