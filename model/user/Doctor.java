package model.user;

import java.util.Map;

import model.appointment.Appointment;

import java.util.List;

public class Doctor implements User {
    private String doctorID;
    private String password;
    private PersonalInfo personalInfo;
    private int numOfPatients = 0;
    private List<Appointment> appointments = null;

    public Doctor(String doctorID, String password, PersonalInfo personalInfo, List<Appointment> appointments,
            int numOfPatients) {
        this.doctorID = doctorID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.appointments = appointments;
        this.numOfPatients = numOfPatients;
    }

    public Doctor(String doctorID, String password, PersonalInfo personalInfo) {
        this.doctorID = doctorID;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    /**
     * Converts the map to a Doctor object
     *
     * @param map the map
     */
    public Doctor(Map<String, String> map) {
        this.convertToObject(map);
    }

    // getters
    public String getModelID() {
        return this.doctorID;
    }

    public String getName() {
        return personalInfo.getName();
    }

    public String getEmail() {
        return personalInfo.getEmail();
    }

    public String getModelEmail() {
        return personalInfo.getEmail();
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public int getNumOfPatients() {
        return numOfPatients;
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

    public void setNumOfPatients(int numOfPatients) {
        this.numOfPatients = numOfPatients;
    }

}
