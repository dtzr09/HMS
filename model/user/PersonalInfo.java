package model.user;

import java.util.Date;

import model.user.enums.Gender;
import utils.iocontrol.Mappable;
import utils.utils.FormatDateTime;

public class PersonalInfo implements Mappable {

    private String name;
    private Gender gender;
    private int age;
    private Date dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    private Date dateOfRegistration;

    public PersonalInfo(String name, Gender gender, int age, Date dateOfBirth, String emailAddress,
            String phoneNumber, Date dateOfRegistration) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfRegistration = dateOfRegistration;
    }

    public PersonalInfo(String name, String emailAddress, Gender gender, int age) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.age = age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    // Display personal information
    public void displayPersonalInfo() {
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        if (dateOfBirth == null) {
            System.out.println("Date of Birth: Not available");
        } else {
            System.out.println("Date of Birth: " + FormatDateTime.toDateOnly(dateOfBirth));
        }
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Date of Registration: " + dateOfRegistration.toString());
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
