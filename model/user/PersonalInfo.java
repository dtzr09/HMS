package model.user;

import java.util.Date;

import utils.iocontrol.Mappable;

public class PersonalInfo implements Mappable {

    private String name;
    private String gender;
    private int age;
    private String dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    private String dateOfModification;

    public PersonalInfo(String name, String gender, int age, String dateOfBirth, String emailAddress,
            String phoneNumber, String dateOfModification) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfModification = dateOfModification;
    }

    public PersonalInfo(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfModification() {
        return dateOfModification;
    }

    // Display personal information
    public void displayPersonalInfo() {
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Date of Modification: " + dateOfModification);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfModification(String dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public void setDateOfModification(Date date) {
        this.dateOfModification = date.toString();
    }
}
