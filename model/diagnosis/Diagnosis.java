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

    public Diagnosis() {
    }

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
     * Converts the map to a Administrator object
     *
     * @param map the map
     */
    public Diagnosis(Map<String, String> map) {
        this.convertToObject(map);
    }

    public String getModelID() {
        return diagnosisID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDiagnosisID() {
        return diagnosisID;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public String getDateOfDiagnosis() {
        return dateOfDiagnosis;
    }

    public void setDateOfDiagnosis(String dateOfDiagnosis) {
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    public void setDiagnosisID(String diagnosisID) {
        this.diagnosisID = diagnosisID;
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

    public void setPrescription(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

}
