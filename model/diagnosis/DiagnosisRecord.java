package model.diagnosis;

import java.util.ArrayList;

public class DiagnosisRecord {
    private String diagnosisID;
    private String disease;
    private String doctorName;
    private ArrayList<String> medicationNames;
    private String dateOfDiagnosis;

    public DiagnosisRecord(String diagnosisID, String disease, String doctorName, ArrayList<String> medicationNames,
            String dateOfDiagnosis) {
        this.diagnosisID = diagnosisID;
        this.disease = disease;
        this.doctorName = doctorName;
        this.medicationNames = medicationNames;
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    public String getDiagnosisID() {
        return diagnosisID;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public ArrayList<String> getMedicationNames() {
        return medicationNames;
    }

    public void setMedicationNames(ArrayList<String> medicationNames) {
        this.medicationNames = medicationNames;
    }

    public String getDateOfDiagnosis() {
        return dateOfDiagnosis;
    }

    public void setDateOfDiagnosis(String dateOfDiagnosis) {
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

}
