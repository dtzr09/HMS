package model.medicalHistory;

import model.diagnosis.Diagnosis;
import model.prescription.Prescription;
import model.user.Patient;

public class MedicalHistory {

    private Patient patient;
    private String allergies;
    private BloodType bloodType;
    private Diagnosis diagnosis;
    private Prescription prescription;

    public MedicalHistory() {
    }

    public MedicalHistory(Patient patient, Diagnosis diagnosis, Prescription prescription, String allergies,
            BloodType bloodType) {
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.allergies = allergies;
        this.bloodType = bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}