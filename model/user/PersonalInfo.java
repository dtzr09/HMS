package model.user;

import java.util.Date;

import model.user.enums.Gender;
import utils.iocontrol.Mappable;

/**
 * Represents the personal information of a user in the hospital management
 * system.
 * Implements the Mappable interface, allowing it to be used in map-based
 * operations.
 */
public class PersonalInfo implements Mappable {

    private String name;
    private Gender gender;
    private int age;
    private String dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    private Date dateOfRegistration;

    /**
     * Constructs a PersonalInfo object with all specified fields.
     *
     * @param name               the name of the person.
     * @param gender             the gender of the person.
     * @param age                the age of the person.
     * @param dateOfBirth        the date of birth of the person.
     * @param emailAddress       the email address of the person.
     * @param phoneNumber        the phone number of the person.
     * @param dateOfRegistration the date when the person registered.
     */
    public PersonalInfo(String name, Gender gender, int age, String dateOfBirth, String emailAddress,
            String phoneNumber, Date dateOfRegistration) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfRegistration = dateOfRegistration;
    }

    /**
     * Constructs a PersonalInfo object with basic fields.
     *
     * @param name               the name of the person.
     * @param emailAddress       the email address of the person.
     * @param gender             the gender of the person.
     * @param age                the age of the person.
     * @param dateOfRegistration the date when the person registered.
     */
    public PersonalInfo(String name, String emailAddress, Gender gender, int age, Date dateOfRegistration) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.age = age;
        this.dateOfRegistration = dateOfRegistration;
    }

    /**
     * Retrieves the name of the person.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the gender of the person.
     *
     * @return the gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Retrieves the age of the person.
     *
     * @return the age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Retrieves the date of birth of the person.
     *
     * @return the date of birth.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Retrieves the email address of the person.
     *
     * @return the email address.
     */
    public String getEmail() {
        return emailAddress;
    }

    /**
     * Retrieves the phone number of the person.
     *
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Retrieves the date of registration.
     *
     * @return the date of registration.
     */
    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    /**
     * Displays the personal information of the person.
     */
    public void displayPersonalInfo() {
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Phone Number: " + phoneNumber);
    }

    /**
     * Sets the name of the person.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the gender of the person.
     *
     * @param gender the gender to set.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Sets the age of the person.
     *
     * @param age the age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the date of birth of the person.
     *
     * @param dateOfBirth the date of birth to set.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets the email address of the person.
     *
     * @param emailAddress the email address to set.
     */
    public void setEmail(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber the phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the date of registration.
     *
     * @param dateOfRegistration the date of registration to set.
     */
    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
