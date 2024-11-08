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

    Date getDateOfBirth();

    int getAge();

    String getPhoneNumber();

    Gender getGender();

    void setName(String name);

    void setEmail(String email);

    void setPhoneNumber(String phoneNumber);

    void setAge(int age);

    void setDateOfBirth(Date dateOfBirth);

    void setPassword(String password);

}
