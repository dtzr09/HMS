package MedicalHistory;

public class Diagnosis {
    private String disease;
    private String doctorID;

    public Diagnosis(){

    }
    public Diagnosis(String disease, String doctorID){
        this.disease = disease;
        this.doctorID = doctorID;
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
