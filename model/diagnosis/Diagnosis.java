package model.diagnosis;

public class Diagnosis {
    private String dianosisID;
    private String disease;
    private String doctorID;

    public Diagnosis(){

    }
    public Diagnosis(String diagnosisID, String disease, String doctorID){
        this.dianosisID = diagnosisID;
        this.disease = disease;
        this.doctorID = doctorID;
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
}
