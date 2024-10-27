package model.user;

import java.util.Map;
import java.util.List;

public class Doctor implements User {
    private String doctorID;
    private String hashedPassword;
    private PersonalInfo personalInfo;
    private int numOfPatients = 0;
    private List<Appointment> appointments = null;

    public Doctor(String doctorID, String hashedPassword, PersonalInfo personalInfo, List<Appointment> appointments,
            int numOfPatients) {
        this.doctorID = doctorID;
        this.hashedPassword = hashedPassword;
        this.personalInfo = personalInfo;
        this.appointments = appointments;
        this.numOfPatients = numOfPatients;
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

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public int getNumOfPatients() {
        return numOfPatients;
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

    public void setNumOfPatients(int numOfPatients) {
        this.numOfPatients = numOfPatients;
    }

}