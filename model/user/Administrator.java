package model.user;

import java.util.Date;
import java.util.Map;

import model.user.enums.Gender;

public class Administrator implements User {
    private String administratorID;
    private PersonalInfo personalInfo;
    private String password;

    /**
     * Constructs an Administrator with specified ID, personal information, and
     * password.
     *
     * @param administratorID the unique identifier for the administrator.
     * @param personalInfo    the personal information of the administrator.
     * @param password        the password for the administrator's account.
     */
    public Administrator(String administratorID, PersonalInfo personalInfo, String password) {
        this.administratorID = administratorID;
        this.personalInfo = personalInfo;
        this.password = password;
    }

    /**
     * Constructs an Administrator by converting a map of attributes.
     *
     * @param map a map of administrator attributes.
     */
    public Administrator(Map<String, String> map) {
        this.convertToObject(map);
    }

    /**
     * Displays the personal information of the administrator.
     */
    public void displayPersonalInfo() {
        personalInfo.displayPersonalInfo();
    }

    /**
     * Sets the personal information of the administrator.
     *
     * @param personalInfo the personal information to set.
     */
    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    /**
     * Retrieves the name of the administrator.
     *
     * @return the name of the administrator.
     */
    public String getName() {
        return personalInfo.getName();
    }

    /**
     * Retrieves the unique identifier for the administrator.
     *
     * @return the administrator ID.
     */
    public String getModelID() {
        return administratorID;
    }

    /**
     * Retrieves the email of the administrator.
     *
     * @return the email of the administrator.
     */
    public String getModelEmail() {
        return personalInfo.getEmail();
    }

    /**
     * Retrieves the password for the administrator's account.
     *
     * @return the password of the administrator.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the administrator's account.
     *
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the email of the administrator.
     *
     * @return the email of the administrator.
     */
    public String getEmail() {
        return personalInfo.getEmail();
    }

    /**
     * Retrieves the date of birth of the administrator.
     *
     * @return the date of birth of the administrator.
     */
    public String getDateOfBirth() {
        return personalInfo.getDateOfBirth();
    }

    /**
     * Retrieves the age of the administrator.
     *
     * @return the age of the administrator.
     */
    public int getAge() {
        return personalInfo.getAge();
    }

    /**
     * Retrieves the phone number of the administrator.
     *
     * @return the phone number of the administrator.
     */
    public String getPhoneNumber() {
        return personalInfo.getPhoneNumber();
    }

    /**
     * Retrieves the date of registration of the administrator.
     *
     * @return the date of registration.
     */
    public Date getDateOfRegistration() {
        return personalInfo.getDateOfRegistration();
    }

    /**
     * Retrieves the gender of the administrator.
     *
     * @return the gender of the administrator.
     */
    public Gender getGender() {
        return personalInfo.getGender();
    }

    /**
     * Sets the email of the administrator.
     *
     * @param email the email to set.
     */
    public void setEmail(String email) {
        personalInfo.setEmail(email);
    }

    /**
     * Sets the name of the administrator.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        personalInfo.setName(name);
    }

    /**
     * Sets the phone number of the administrator.
     *
     * @param phoneNumber the phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        personalInfo.setPhoneNumber(phoneNumber);
    }

    /**
     * Sets the age of the administrator.
     *
     * @param age the age to set.
     */
    public void setAge(int age) {
        personalInfo.setAge(age);
    }

    /**
     * Sets the date of birth of the administrator.
     *
     * @param dateOfBirth the date of birth to set.
     */
    public void setDateOfBirth(String dateOfBirth) {
        personalInfo.setDateOfBirth(dateOfBirth);
    }
}
