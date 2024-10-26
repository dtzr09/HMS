package MedicalHistory;

public class MedicalHistory {

    private Patient patient;
    private String allergies;
    private BloodType bloodType;
    private Prescription prescription;
    private Double height;
    private Double weight;

    public MedicalHistory(){}
    public MedicalHistory(Patient patient, Prescription prescription){
        this.patient = patient;
        this.prescription = prescription;
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

    public Double getHeight() {
        return height;
    }
    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}