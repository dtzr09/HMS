package model.user;

import java.util.Date;
import java.util.Map;

import model.user.enums.Gender;

/**
 * Represents a Pharmacist in the hospital management system.
 * A Pharmacist implements the User interface and contains information
 * about their ID, personal details, and password.
 */
public class Pharmacist implements User {
    private String pharmacistID;
    private PersonalInfo personalInfo;
    private String password;

    /**
     * Constructs a Pharmacist with specified ID, personal information, and
     * password.
     *
     * @param pharmacistID the unique identifier for the pharmacist.
     * @param personalInfo the personal information of the pharmacist.
     * @param password     the password for the pharmacist's account.
     */
    public Pharmacist(String pharmacistID, PersonalInfo personalInfo, String password) {
        this.pharmacistID = pharmacistID;
        this.personalInfo = personalInfo;
        this.password = password;
    }

    /**
     * Constructs a Pharmacist by converting a map of attributes.
     *
     * @param map a map of pharmacist attributes.
     */
    public Pharmacist(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Retrieves the name of the pharmacist.
     *
     * @return the name of the pharmacist.
     */
    public String getName() {
        return personalInfo.getName();
    }

    /**
     * Retrieves the personal information of the pharmacist.
     *
     * @return the personal information.
     */
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    /**
     * Retrieves the email of the pharmacist.
     *
     * @return the email.
     */
    public String getEmail() {
        return personalInfo.getEmail();
    }

    /**
     * Retrieves the unique identifier for the pharmacist.
     *
     * @return the pharmacist ID.
     */
    public String getModelID() {
        return pharmacistID;
    }

    /**
     * Retrieves the password for the pharmacist's account.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the email of the pharmacist.
     *
     * @return the email.
     */
    public String getModelEmail() {
        return personalInfo.getEmail();
    }

    /**
     * Retrieves the date of birth of the pharmacist.
     *
     * @return the date of birth.
     */
    public String getDateOfBirth() {
        return personalInfo.getDateOfBirth();
    }

    /**
     * Retrieves the age of the pharmacist.
     *
     * @return the age.
     */
    public int getAge() {
        return personalInfo.getAge();
    }

    /**
     * Retrieves the date of registration of the pharmacist.
     *
     * @return the date of registration.
     */
    public Date getDateOfRegistration() {
        return personalInfo.getDateOfRegistration();
    }

    /**
     * Retrieves the phone number of the pharmacist.
     *
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return personalInfo.getPhoneNumber();
    }

    /**
     * Retrieves the gender of the pharmacist.
     *
     * @return the gender.
     */
    public Gender getGender() {
        return personalInfo.getGender();
    }

    /**
     * Sets the name of the pharmacist.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        personalInfo.setName(name);
    }

    /**
     * Sets the password for the pharmacist's account.
     *
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the personal information of the pharmacist.
     *
     * @param personalInfo the personal information to set.
     */
    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    /**
     * Sets the email of the pharmacist.
     *
     * @param email the email to set.
     */
    public void setEmail(String email) {
        personalInfo.setEmail(email);
    }

    /**
     * Sets the phone number of the pharmacist.
     *
     * @param phoneNumber the phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        personalInfo.setPhoneNumber(phoneNumber);
    }

    /**
     * Sets the age of the pharmacist.
     *
     * @param age the age to set.
     */
    public void setAge(int age) {
        personalInfo.setAge(age);
    }

    /**
     * Sets the date of birth of the pharmacist.
     *
     * @param dateOfBirth the date of birth to set.
     */
    public void setDateOfBirth(String dateOfBirth) {
        personalInfo.setDateOfBirth(dateOfBirth);
    }
}
