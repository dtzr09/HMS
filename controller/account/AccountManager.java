package controller.account;

import controller.account.password.PasswordManager;
import controller.account.user.UserManager;
import model.user.User;
import model.user.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PasswordIncorrectException;

public class AccountManager {
    private String password = "password";

    /**
     * Adds a new user to the database
     *
     * @param userType the type of the user to be added
     * @param userID   the ID of the user to be added
     * @param password the password of the user to be added
     * @return the user that is added
     * @throws PasswordIncorrectException if the password is incorrect
     * @throws ModelNotFoundException     if the user is not found
     */
    public static User login(UserType userType, String userID, String password)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserManager.findUser(userID, userType);
        if (PasswordManager.checkPassword(user, password)) {
            return user;
        } else {
            throw new PasswordIncorrectException();
        }
    }

    public void logout() {
        // logout
    }

    public boolean validatePassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public void register(String username, String password) {
        // register
    }

    public void viewProfile() {
        // viewProfile
    }

    public void changePassword(String password) {
        // changePassword
    }

    public void updateProfile() {
        // updateProfile
    }

    public void loadDoctor() {
        // loadDoctor
    }

    public void loadPatient() {
        // loadPatient
    }

    public void loadAPharmacist() {
        // loadAPharmacist
    }

    public void loadAdministrator() {
        // loadAdministrator
    }

    public void loadMedicine() {
        // loadMedicine
    }

}
