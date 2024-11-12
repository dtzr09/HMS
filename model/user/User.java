package model.user;

import java.util.Date;

import model.Model;
import model.user.enums.Gender;

/**
 * Represents a user in the hospital management system.
 * Extends the Model interface and provides methods to get and set
 * basic user details such as ID, email, name, password, date of birth,
 * registration date, age, phone number, and gender.
 */
public interface User extends Model {

    /**
     * Gets the unique model identifier for the user.
     * 
     * @return the model ID of the user.
     */
    String getModelID();

    /**
     * Gets the model email associated with the user.
     * 
     * @return the model email of the user.
     */
    String getModelEmail();

    /**
     * Gets the user's email address.
     * 
     * @return the email address of the user.
     */
    String getEmail();

    /**
     * Gets the name of the user.
     * 
     * @return the name of the user.
     */
    String getName();

    /**
     * Gets the user's password.
     * 
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Gets the date of birth of the user.
     * 
     * @return the date of birth of the user in String format.
     */
    String getDateOfBirth();

    /**
     * Gets the date the user registered in the system.
     * 
     * @return the date of registration of the user.
     */
    Date getDateOfRegistration();

    /**
     * Gets the age of the user.
     * 
     * @return the age of the user.
     */
    int getAge();

    /**
     * Gets the phone number of the user.
     * 
     * @return the phone number of the user.
     */
    String getPhoneNumber();

    /**
     * Gets the gender of the user.
     * 
     * @return the gender of the user.
     */
    Gender getGender();

    /**
     * Sets the name of the user.
     * 
     * @param name the new name of the user.
     */
    void setName(String name);

    /**
     * Sets the email of the user.
     * 
     * @param email the new email address of the user.
     */
    void setEmail(String email);

    /**
     * Sets the phone number of the user.
     * 
     * @param phoneNumber the new phone number of the user.
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * Sets the age of the user.
     * 
     * @param age the new age of the user.
     */
    void setAge(int age);

    /**
     * Sets the date of birth of the user.
     * 
     * @param dateOfBirth the new date of birth of the user.
     */
    void setDateOfBirth(String dateOfBirth);

    /**
     * Sets the password of the user.
     * 
     * @param password the new password of the user.
     */
    void setPassword(String password);
}
