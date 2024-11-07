package model.prescription;

import java.util.ArrayList;
import java.util.Date;

import model.medication.Medication;

public class Prescription {
    private String prescriptionID;
    private String patientID;
    private String pharmacistID;
    private String doctorID;
    private ArrayList<Medication> medication;
    private Date dateOfPrescription;
    private String drugInstructions;
    private PrescriptionStatus prescriptionStatus;

    public Prescription(){}
    
    public Prescription(String prescriptionID, String patientID, String pharmacistID, String doctorID,
            ArrayList<Medication> medication, Date dateOfPrescription, String drugInstructions,
            PrescriptionStatus prescriptionStatus) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.pharmacistID = pharmacistID;
        this.doctorID = doctorID;
        this.medication = medication;
        this.dateOfPrescription = dateOfPrescription;
        this.drugInstructions = drugInstructions;
        this.prescriptionStatus = prescriptionStatus;
    }

    public Prescription(String prescriptionID, String patientID,
            String doctorID,
            ArrayList<Medication> medication, Date dateOfPrescription,
            String drugInstructions,
            PrescriptionStatus prescriptionStatus) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medication = medication;
        this.dateOfPrescription = dateOfPrescription;
        this.drugInstructions = drugInstructions;
        this.prescriptionStatus = prescriptionStatus;
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

    public ArrayList<Medication> getMedication() {
        return medication;
    }

    public void setMedication(ArrayList<Medication> medication) {
        this.medication = medication;
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
