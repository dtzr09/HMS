package model.diagnosis;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import model.prescription.Prescription;

public class Diagnosis {
    private String dianosisID;
    private String disease;
    private String doctorID;
    private ArrayList<Prescription> prescriptions = null;

    public Diagnosis(){

    }
    public Diagnosis(String diagnosisID, String disease, String doctorID, ArrayList<Prescription> prescriptions){
        this.dianosisID = diagnosisID;
        this.disease = disease;
        this.doctorID = doctorID;
        this.prescriptions = prescriptions;
    }

    public String getDianosisID() {
        return dianosisID;
    }

    public void setDianosisID(String dianosisID) {
        this.dianosisID = dianosisID;
    }

    public String getDisease() {
        return disease;
    }
    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }
    public void setPrescriptions(ArrayList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
    public Prescription findPrescription(String prescriptionid){
        for (Prescription prescription : this.prescriptions) {
            if (prescription.getPrescriptionID().equals(prescriptionid)) {
                return prescription;
            }
        } throw new NoSuchElementException("Prescription with ID " + prescriptionid + " not found!");

    }
    public void addPrescriptions(Prescription prescription){    // Adding one prescription object
        this.prescriptions.add(prescription);
    }
}
