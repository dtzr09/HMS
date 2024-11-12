package model.user;

import java.util.Date;

import model.Model;
import model.user.enums.Gender;

public interface User extends Model {

    String getModelID();

    String getModelEmail();

    String getEmail();

    String getName();

    String getPassword();

    String getDateOfBirth();

    Date getDateOfRegistration();

    int getAge();

    String getPhoneNumber();

    Gender getGender();

    void setName(String name);

    void setEmail(String email);

    void setPhoneNumber(String phoneNumber);

    void setAge(int age);

    void setDateOfBirth(String dateOfBirth);

    void setPassword(String password);

}
