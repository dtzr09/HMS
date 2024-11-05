package controller.user;

import controller.account.AccountManager;
import database.user.AdministratorDatabase;
import model.user.enums.Gender;
import model.user.enums.UserType;
import utils.exceptions.ModelNotFoundException;
import utils.exceptions.UserAlreadyExistsException;
import utils.exceptions.UserCannotBeFoundException;

public class AdministratorManager {

    public static boolean isRepositoryEmpty() {
        return AdministratorDatabase.getDB().isEmpty();
    }

    public static void addNewHospitalStaff(String email, String name, Gender gender, int age, UserType userType)
            throws ModelNotFoundException, UserAlreadyExistsException {
        try {
            AccountManager.register(email, name, gender, age, userType);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("User already exists");
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(userType, email);
        }
    }

    public static void removeNewHospitalStaff(String email, UserType userType)
            throws ModelNotFoundException, UserCannotBeFoundException {
        try {
            AccountManager.removeUser(email, userType);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("User not found");
        } catch (UserCannotBeFoundException e) {
            throw new UserCannotBeFoundException(email, userType);
        }
    }

}
