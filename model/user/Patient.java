package model.user;

import model.diagnosis.Diagnosis;
import model.medicalHistory.*;
import java.util.Map;
import java.util.List;

public class Patient implements User {
    private String patientID;
    private String password;
    private PersonalInfo personalInfo;
    private String allergies;
    private BloodType bloodType;
    private List<Appointment> appointments = null;
    private List<Diagnosis> diagnosis = null;

    public Patient(String patientID, String password, PersonalInfo personalInfo, String allergies, BloodType bloodType, List<Appointment> appointments , List<Diagnosis> diagnosis) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.allergies = allergies;
        this.bloodType = bloodType;
        this.appointments = appointments;
        this.diagnosis = diagnosis;
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

    public String getAllergies() {
        return allergies;
    }
    public BloodType getBloodType() {
        return bloodType;
    }
    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
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
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }
    public void addDiagnosis(Diagnosis diagnosis){      //For adding one new diagnosis
        this.diagnosis.add(diagnosis);
    }
}
