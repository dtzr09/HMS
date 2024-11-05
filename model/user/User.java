package model.user;

import model.Model;

public interface User extends Model {

    String getModelID();

    String getModelEmail();

    String getEmail();

    String getName();

    String getPassword();

    void setPassword(String password);

}
