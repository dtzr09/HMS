package model.user;

import java.util.Map;
import java.util.List;

public class Patient implements User {
    private String patientID;
    private String hashedPassword;
    private PersonalInfo personalInfo;
    private List<Appointment> appointments = null;
    // private String List<MedicalHistory> medicalHistory;

    public Patient(String patientID, String hashedPassword, PersonalInfo personalInfo, List<Appointment> appointments) {
        this.patientID = patientID;
        this.hashedPassword = hashedPassword;
        this.personalInfo = personalInfo;
        this.appointments = appointments;
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

    public String getHashedPassword() {
        return this.hashedPassword;
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
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

}
