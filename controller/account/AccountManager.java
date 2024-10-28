package controller.account;

import controller.account.password.PasswordManager;
import controller.account.user.UserManager;
import model.user.User;
import model.user.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PasswordIncorrectException;

public class AccountManager {
    private String password = "password";

    public static boolean userExist(String email, UserType userType) {
        try {
            User user = UserManager.findUser(email, userType);
            if (user != null) {
                return true;
            }
            return false;
        } catch (ModelNotFoundException e) {
            return false;
        }

    }

    public static User login(UserType userType, String email, String password)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserManager.findUser(email, userType);
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
