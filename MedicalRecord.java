package AccountManager;

public class MedicalRecord {
    private String diagnosis;
    private String treatment;
    private String date;

    public MedicalRecord(String diagnosis, String treatment, String date) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Diagnosis: " + diagnosis + ", Treatment: " + treatment;
    }
}
