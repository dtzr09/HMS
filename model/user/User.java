package model.user;

import model.Model;

public interface User extends Model {

    String getModelID();

    String getEmail();

    String getName();

    String getHashedPassword();

    void setHashedPassword(String hashedPassword);

}
