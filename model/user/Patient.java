package model.user;

import model.user.enums.BloodType;
import model.user.enums.Gender;

import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

public class Patient implements User {
    private String patientID;
    private String doctorID;
    private String password;
    private PersonalInfo personalInfo;
    private ArrayList<String> allergies;
    private BloodType bloodType;
    private ArrayList<String> diagnosisIDs = new ArrayList<>();

    /**
     * Default constructor for Patient.
     */
    public Patient() {
    }

    /**
     * Constructs a Patient with all specified fields.
     *
     * @param patientID    the unique identifier for the patient.
     * @param password     the password for the patient's account.
     * @param personalInfo the personal information of the patient.
     * @param allergies    a list of allergies of the patient.
     * @param bloodType    the blood type of the patient.
     * @param diagnosisIDs a list of diagnosis IDs associated with the patient.
     * @param doctorID     the unique identifier for the patient's doctor.
     */
    public Patient(String patientID, String password, PersonalInfo personalInfo, ArrayList<String> allergies,
            BloodType bloodType, ArrayList<String> diagnosisIDs, String doctorID) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.allergies = allergies;
        this.bloodType = bloodType;
        this.diagnosisIDs = diagnosisIDs;
        this.doctorID = doctorID;
    }

    /**
     * Constructs a Patient with basic fields.
     *
     * @param patientID    the unique identifier for the patient.
     * @param personalInfo the personal information of the patient.
     * @param password     the password for the patient's account.
     * @param doctorID     the unique identifier for the patient's doctor.
     */
    public Patient(String patientID, PersonalInfo personalInfo, String password, String doctorID) {
        this.patientID = patientID;
        this.password = password;
        this.personalInfo = personalInfo;
        this.doctorID = doctorID;
    }

    /**
     * Constructs a Patient by converting a map of attributes.
     *
     * @param map a map of patient attributes.
     */
    public Patient(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the unique identifier for the patient.
     *
     * @return the patient ID.
     */
    public String getModelID() {
        return this.patientID;
    }

    /**
     * Retrieves the patient ID.
     *
     * @return the patient ID.
     */
    public String getPatientID() {
        return this.patientID;
    }

    /**
     * Retrieves the password for the patient's account.
     *
     * @return the password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves the email of the patient.
     *
     * @return the email.
     */
    public String getEmail() {
        return personalInfo.getEmail();
    }

    /**
     * Retrieves the email of the patient.
     *
     * @return the email.
     */
    public String getModelEmail() {
        return personalInfo.getEmail();
    }

    /**
     * Retrieves the date of registration of the patient.
     *
     * @return the date of registration.
     */
    public Date getDateOfRegistration() {
        return personalInfo.getDateOfRegistration();
    }

    /**
     * Retrieves the name of the patient.
     *
     * @return the name.
     */
    public String getName() {
        return personalInfo.getName();
    }

    /**
     * Retrieves the personal information of the patient.
     *
     * @return the personal information.
     */
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    /**
     * Retrieves the list of allergies of the patient.
     *
     * @return the list of allergies.
     */
    public ArrayList<String> getAllergies() {
        return allergies;
    }

    /**
     * Retrieves the blood type of the patient.
     *
     * @return the blood type.
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Retrieves the list of diagnosis IDs associated with the patient.
     *
     * @return the list of diagnosis IDs.
     */
    public ArrayList<String> getDiagnosisID() {
        return diagnosisIDs;
    }

    /**
     * Retrieves the doctor ID associated with the patient.
     *
     * @return the doctor ID.
     */
    public String getDoctorID() {
        return doctorID;
    }

    /**
     * Retrieves the date of birth of the patient.
     *
     * @return the date of birth.
     */
    public String getDateOfBirth() {
        return personalInfo.getDateOfBirth();
    }

    /**
     * Retrieves the age of the patient.
     *
     * @return the age.
     */
    public int getAge() {
        return personalInfo.getAge();
    }

    /**
     * Retrieves the phone number of the patient.
     *
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return personalInfo.getPhoneNumber();
    }

    /**
     * Retrieves the gender of the patient.
     *
     * @return the gender.
     */
    public Gender getGender() {
        return personalInfo.getGender();
    }

    /**
     * Sets the password for the patient's account.
     *
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the doctor ID associated with the patient.
     *
     * @param doctorID the doctor ID to set.
     */
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * Sets the personal information of the patient.
     *
     * @param personalInfo the personal information to set.
     */
    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    /**
     * Sets the list of allergies of the patient.
     *
     * @param allergies the list of allergies to set.
     */
    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    /**
     * Sets the blood type of the patient.
     *
     * @param bloodType the blood type to set.
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Sets the list of diagnosis IDs associated with the patient.
     *
     * @param diagnosisIDs the list of diagnosis IDs to set.
     */
    public void setDiagnosisID(ArrayList<String> diagnosisIDs) {
        this.diagnosisIDs = diagnosisIDs;
    }

    /**
     * Adds a diagnosis ID to the list of diagnosis IDs associated with the patient.
     *
     * @param diagnosisID the diagnosis ID to add.
     */
    public void addDiagnosisID(String diagnosisID) {
        this.diagnosisIDs.add(diagnosisID);
    }

    /**
     * Sets the email of the patient.
     *
     * @param email the email to set.
     */
    public void setEmail(String email) {
        personalInfo.setEmail(email);
    }

    /**
     * Sets the name of the patient.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        personalInfo.setName(name);
    }

    /**
     * Sets the phone number of the patient.
     *
     * @param phoneNumber the phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        personalInfo.setPhoneNumber(phoneNumber);
    }

    /**
     * Sets the age of the patient.
     *
     * @param age the age to set.
     */
    public void setAge(int age) {
        personalInfo.setAge(age);
    }

    /**
     * Sets the date of birth of the patient.
     *
     * @param dateOfBirth the date of birth to set.
     */
    public void setDateOfBirth(String dateOfBirth) {
        personalInfo.setDateOfBirth(dateOfBirth);
    }

    /**
     * Adds an allergy to the list of allergies associated with the patient.
     *
     * @param allergy the allergy to add.
     */
    public void addAllergy(String allergy) {
        this.allergies.add(allergy);
    }
}
