package model.diagnosis;

import java.util.Date;

import model.prescription.Prescription;

public class Diagnosis {
    private String dianosisID;
    private String disease;
    private String doctorID;
    private Prescription prescription;
    private Date dateOfDiagnosis;

    public Diagnosis(String diagnosisID, String disease, String doctorID, Prescription prescriptions,
            Date dateOfDiagnosis) {
        this.dianosisID = diagnosisID;
        this.disease = disease;
        this.doctorID = doctorID;
        this.prescription = prescriptions;
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    public String getDiagnosisID() {
        return dianosisID;
    }

    public String getDateOfDiagnosis() {
        return dateOfDiagnosis.toString();
    }

    public void setDateOfDiagnosis(Date dateOfDiagnosis) {
        this.dateOfDiagnosis = dateOfDiagnosis;
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

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    // public Prescription findPrescription(String prescriptionid) {
    // for (Prescription prescription : this.prescriptions) {
    // if (prescription.getPrescriptionID().equals(prescriptionid)) {
    // return prescription;
    // }
    // }
    // throw new NoSuchElementException("Prescription with ID " + prescriptionid + "
    // not found!");

    // }

    // public void addPrescriptions(Prescription prescription) { // Adding one
    // prescription object
    // this.prescriptions.add(prescription);
    // }
}
