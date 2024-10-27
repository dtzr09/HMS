package model.user;

import model.Model;

public interface User extends Model {
    /**
     * Gets the user ID of the user.
     *
     * @return the ID of the user.
     */
    String getModelID();

    /**
     * Gets the username of the user
     *
     * @return the name of the user
     */
    String getName();

    /**
     * Gets the hashed password of the user
     *
     * @return the hashed password of the user
     */
    String getHashedPassword();

    /**
     * Sets the hashed password of the user
     *
     * @param hashedPassword the hashed password of the user
     */
    void setHashedPassword(String hashedPassword);

}
