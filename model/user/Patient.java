package model.user;

import model.medicalHistory.*;
import java.util.Map;
import java.util.List;

public class Patient implements User {
    private String patientID;
    private String password;
    private PersonalInfo personalInfo;
    private List<Appointment> appointments = null;
    private List<MedicalHistory> medicalHistories = null;

    public Patient(String patientID, String password, PersonalInfo personalInfo, List<Appointment> appointments , List<MedicalHistory> medicalHistory) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.appointments = appointments;
        this.medicalHistories = medicalHistory;
    }

    public Patient(String patientID, PersonalInfo personalInfo, String password) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    /**
     * Converts the map to a Patient object
     *
     * @param map the map
     */
    public Patient(Map<String, String> map) {
        this.convertToObject(map);
    }

    // getters
    public String getModelID() {
        return this.patientID;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return personalInfo.getEmail();
    }

    public String getName() {
        return personalInfo.getName();
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<MedicalHistory> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(MedicalHistory medicalHistory) { // Adding single history
        this.medicalHistories.add(medicalHistory);
    }

}
