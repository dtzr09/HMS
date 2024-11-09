package model.user;

import java.util.Map;

import model.appointment.Appointment;
import model.user.enums.Gender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Doctor implements User {
    private String doctorID;
    private String password;
    private PersonalInfo personalInfo;
    private int numOfPatients = 0;
    private ArrayList<Appointment> appointments = null;
    private ArrayList<Appointment> appointmentRequests = null;
    private Map<String, List<String>> appointmentAvailability;

    public Doctor(String doctorID, String password, PersonalInfo personalInfo, ArrayList<Appointment> appointments,
            int numOfPatients, ArrayList<Appointment> appointmentRequests,
            Map<String, List<String>> appointmentAvailability) {
        this.doctorID = doctorID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.appointments = appointments;
        this.numOfPatients = numOfPatients;
        this.appointmentRequests = appointmentRequests;
        this.appointmentAvailability = appointmentAvailability;
    }

    public Doctor(String doctorID, PersonalInfo personalInfo, String password) {
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

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<Appointment> getAppointmentRequests() {
        return appointmentRequests;
    }

    public int getNumOfPatients() {
        return numOfPatients;
    }

    public Map<String, List<String>> getAppointmentAvailability() {
        return appointmentAvailability;
    }

    public Date getDateOfBirth() {
        return personalInfo.getDateOfBirth();
    }

    public int getAge() {
        return personalInfo.getAge();
    }

    public String getPhoneNumber() {
        return personalInfo.getPhoneNumber();
    }

    public Gender getGender() {
        return personalInfo.getGender();
    }

    // setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setNumOfPatients(int numOfPatients) {
        this.numOfPatients = numOfPatients;
    }

    public void setAppointmentRequests(ArrayList<Appointment> appointmentRequests) {
        this.appointmentRequests = appointmentRequests;
    }

    public void setAppointmentAvailability(Map<String, List<String>> appointmentAvailability) {
        this.appointmentAvailability = appointmentAvailability;
    }

    public void setEmail(String email) {
        personalInfo.setEmail(email);
    }

    public void setName(String name) {
        personalInfo.setName(name);
    }

    public void setPhoneNumber(String phoneNumber) {
        personalInfo.setPhoneNumber(phoneNumber);
    }

    public void setAge(int age) {
        personalInfo.setAge(age);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        personalInfo.setDateOfBirth(dateOfBirth);
    }
}
