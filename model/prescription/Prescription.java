package model.prescription;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import model.Model;

public class Prescription implements Model {
    private String prescriptionID;
    private String patientID;
    private String pharmacistID;
    private String doctorID;
    private ArrayList<String> medicationIDs;
    private Date dateOfPrescription;
    private String drugInstructions;
    private PrescriptionStatus prescriptionStatus;

    public Prescription() {
    }

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

    public Prescription(String prescriptionID, String patientID,
            String doctorID,
            ArrayList<String> medicationIDs, Date dateOfPrescription,
            String drugInstructions,
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
     * Converts the map to a Medication object
     *
     * @param map the map
     */
    public Prescription(Map<String, String> map) {
        this.convertToObject(map);
    }

    public String getModelID() {
        return prescriptionID;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatient(String patientID) {
        this.patientID = patientID;
    }

    public String getPharmacist() {
        return pharmacistID;
    }

    public void setPharmacist(String pharmacistID) {
        this.pharmacistID = pharmacistID;
    }

    public String getdoctorID() {
        return doctorID;
    }

    public void setdoctor(String doctorID) {
        this.doctorID = doctorID;
    }

    public ArrayList<String> getMedicationIDs() {
        return medicationIDs;
    }

    public void setMedicationIDs(ArrayList<String> medicationIDs) {
        this.medicationIDs = medicationIDs;
    }

    public Date getDateOfPrescription() {
        return dateOfPrescription;
    }

    public void setDateOfPrescription(Date dateOfPrescription) {
        this.dateOfPrescription = dateOfPrescription;
    }

    public String getDrugInstructions() {
        return drugInstructions;
    }

    public void setDrugInstructions(String drugInstructions) {
        this.drugInstructions = drugInstructions;
    }

    public PrescriptionStatus getPrescriptionStatus() {
        return this.prescriptionStatus;
    }

    public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

}
