package controller.account;

import controller.authentication.PasswordManager;
import controller.user.UserManager;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.PasswordIncorrectException;

public class AccountManager {
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

    public static User register(String email, String name, UserType userType) throws ModelNotFoundException {
        try {
            User user = UserManager.createUser(email, name, userType, "password");
            return user;
        } catch (Exception e) {
            System.out.println("Error registering user");
        }
        return null;
    }

    public static void changePassword(UserType userType, String email, String oldPassword, String newPassword)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserManager.findUser(email, userType);
        PasswordManager.changePassword(user, oldPassword, newPassword);
        UserManager.updateUser(user);
    }

    public void logout() {
        // logout
    }

    public void viewProfile() {
        // viewProfile
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
