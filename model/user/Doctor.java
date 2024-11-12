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
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private Map<String, List<String>> appointmentAvailability;

    /**
     * Constructs a Doctor with specified ID, password, personal information,
     * appointments,
     * number of patients, and appointment availability.
     *
     * @param doctorID                the unique identifier for the doctor.
     * @param password                the password for the doctor's account.
     * @param personalInfo            the personal information of the doctor.
     * @param appointments            a list of appointments associated with the
     *                                doctor.
     * @param numOfPatients           the number of patients assigned to the doctor.
     * @param appointmentAvailability a map of available appointment slots.
     */
    public Doctor(String doctorID, String password, PersonalInfo personalInfo, ArrayList<Appointment> appointments,
            int numOfPatients, Map<String, List<String>> appointmentAvailability) {
        this.doctorID = doctorID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.appointments = appointments;
        this.numOfPatients = numOfPatients;
        this.appointmentAvailability = appointmentAvailability;
    }

    /**
     * Constructs a Doctor with basic fields.
     *
     * @param doctorID     the unique identifier for the doctor.
     * @param personalInfo the personal information of the doctor.
     * @param password     the password for the doctor's account.
     */
    public Doctor(String doctorID, PersonalInfo personalInfo, String password) {
        this.doctorID = doctorID;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    /**
     * Constructs a Doctor by converting a map of attributes.
     *
     * @param map a map of doctor attributes.
     */
    public Doctor(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the unique identifier for the doctor.
     *
     * @return the doctor ID.
     */
    public String getModelID() {
        return this.doctorID;
    }

    /**
     * Retrieves the name of the doctor.
     *
     * @return the name of the doctor.
     */
    public String getName() {
        return personalInfo.getName();
    }

    /**
     * Retrieves the email of the doctor.
     *
     * @return the email of the doctor.
     */
    public String getEmail() {
        return personalInfo.getEmail();
    }

    /**
     * Retrieves the email of the doctor.
     *
     * @return the email of the doctor.
     */
    public String getModelEmail() {
        return personalInfo.getEmail();
    }

    /**
     * Retrieves the personal information of the doctor.
     *
     * @return the personal information.
     */
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    /**
     * Retrieves the date of registration of the doctor.
     *
     * @return the date of registration.
     */
    public Date getDateOfRegistration() {
        return personalInfo.getDateOfRegistration();
    }

    /**
     * Retrieves the password of the doctor.
     *
     * @return the password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves the list of appointments associated with the doctor.
     *
     * @return the list of appointments.
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Retrieves the number of patients assigned to the doctor.
     *
     * @return the number of patients.
     */
    public int getNumOfPatients() {
        return numOfPatients;
    }

    /**
     * Retrieves the map of available appointment slots.
     *
     * @return the appointment availability map.
     */
    public Map<String, List<String>> getAppointmentAvailability() {
        return appointmentAvailability;
    }

    /**
     * Retrieves the date of birth of the doctor.
     *
     * @return the date of birth.
     */
    public String getDateOfBirth() {
        return personalInfo.getDateOfBirth();
    }

    /**
     * Retrieves the age of the doctor.
     *
     * @return the age.
     */
    public int getAge() {
        return personalInfo.getAge();
    }

    /**
     * Retrieves the phone number of the doctor.
     *
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return personalInfo.getPhoneNumber();
    }

    /**
     * Retrieves the gender of the doctor.
     *
     * @return the gender.
     */
    public Gender getGender() {
        return personalInfo.getGender();
    }

    /**
     * Sets the password for the doctor's account.
     *
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the personal information of the doctor.
     *
     * @param personalInfo the personal information to set.
     */
    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    /**
     * Sets the list of appointments associated with the doctor.
     *
     * @param appointments the list of appointments to set.
     */
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Sets the number of patients assigned to the doctor.
     *
     * @param numOfPatients the number of patients to set.
     */
    public void setNumOfPatients(int numOfPatients) {
        this.numOfPatients = numOfPatients;
    }

    /**
     * Sets the map of available appointment slots.
     *
     * @param appointmentAvailability the appointment availability map to set.
     */
    public void setAppointmentAvailability(Map<String, List<String>> appointmentAvailability) {
        this.appointmentAvailability = appointmentAvailability;
    }

    /**
     * Sets the email of the doctor.
     *
     * @param email the email to set.
     */
    public void setEmail(String email) {
        personalInfo.setEmail(email);
    }

    /**
     * Sets the name of the doctor.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        personalInfo.setName(name);
    }

    /**
     * Sets the phone number of the doctor.
     *
     * @param phoneNumber the phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        personalInfo.setPhoneNumber(phoneNumber);
    }

    /**
     * Sets the age of the doctor.
     *
     * @param age the age to set.
     */
    public void setAge(int age) {
        personalInfo.setAge(age);
    }

    /**
     * Sets the date of birth of the doctor.
     *
     * @param dateOfBirth the date of birth to set.
     */
    public void setDateOfBirth(String dateOfBirth) {
        personalInfo.setDateOfBirth(dateOfBirth);
    }

    /**
     * Adds an appointment to the list of appointments associated with the doctor.
     *
     * @param appointment the appointment to add.
     */
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
}
