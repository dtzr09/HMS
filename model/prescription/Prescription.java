package model.prescription;

import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private String prescriptionID;
    private Patient patient;
    private Pharmacist pharmacist;
    private Doctor doctorName;
    private ArrayList<Medication> medication;
    private Date dateOfPrescription;
    private String drugInstructions;
    private PrescriptionStatus prescriptionStatus;

    public Prescription(){
    }
    public Prescription(String prescriptionID, Patient patient, Pharmacist pharmacist, Doctor doctorName){
        this.prescriptionID = prescriptionID;
        this.patient = patient;
        this.pharmacist = pharmacist;
        this.doctorName = doctorName;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }
    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }
    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Doctor getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(Doctor doctorName) {
        this.doctorName = doctorName;
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
        return prescriptionStatus;
    }
    public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

}
