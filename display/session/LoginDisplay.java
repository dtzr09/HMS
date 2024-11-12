package display.session;

import controller.account.AccountManager;
import display.user.AdministratorDisplay;
import display.user.DoctorDisplay;
import display.user.PatientDisplay;
import display.user.PharmacistDisplay;
import model.user.Administrator;
import model.user.Doctor;
import model.user.Patient;
import model.user.Pharmacist;
import model.user.User;
import model.user.enums.UserType;
import utils.exceptions.PageBackException;
import utils.exceptions.PasswordIncorrectException;
import utils.iocontrol.CustScanner;

/**
 * The {@code LoginDisplay} class provides a user interface for users to log in
 * based on their user type (e.g., Administrator, Pharmacist, Doctor, Patient).
 * It handles user input for email and password, verifies the existence of the
 * user,
 * and directs authenticated users to the appropriate display based on their
 * role.
 * If authentication fails, users are given options to retry, quit, or register.
 */
public class LoginDisplay {

    /**
     * Displays the login interface for the specified {@code UserType}.
     * This method prompts the user for their email and password, validates the
     * input, and verifies the existence of the user in the system. If the user
     * is successfully authenticated, they are directed to their respective
     * user display. In case of authentication failure, the user is presented with
     * options to retry, quit, or register.
     * 
     * @param userType the {@code UserType} representing the type of user attempting
     *                 to log in (e.g., Administrator, Pharmacist, Doctor, Patient).
     * @throws PageBackException if the user opts to go back to the previous menu.
     */
    public static void login(UserType userType) throws PageBackException {
        ClearDisplay.ClearConsole();
        System.out.println("========================================");
        System.out.println("You are logging in as a " + userType.toString().toLowerCase());
        System.out.print("Enter your email address: ");
        String email = CustScanner.getStrChoice();

        while (email.isEmpty()) {
            System.out.println("Email cannot be empty.");
            System.out.print("Enter your email address: ");
            email = CustScanner.getStrChoice();
        }

        if (!AccountManager.userExist(email, userType)) {
            System.out.printf(
                    "Email does not exist. Press q to quit, r to retry, or any other key to register.");
            String choice = CustScanner.getStrChoice();
            if (choice.equalsIgnoreCase("q")) {
                System.exit(0);
            } else if (choice.equalsIgnoreCase("r")) {
                login(userType);
            }
            RegisterDisplay.registerDisplay();
        }

        System.out.print("Enter your password: ");
        String password = CustScanner.getPassword();
        while (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            System.out.print("Enter your password: ");
            password = CustScanner.getPassword();
        }

        try {
            User user = AccountManager.login(userType, email, password);

            switch (userType) {
                case ADMINISTRATOR -> AdministratorDisplay.administratorDisplay((Administrator) user);
                case PHARMACIST -> PharmacistDisplay.pharmacistDisplay((Pharmacist) user);
                case DOCTOR -> DoctorDisplay.doctorDisplay((Doctor) user);
                case PATIENT -> PatientDisplay.patientDisplay((Patient) user);
                default -> throw new IllegalStateException("Unexpected value: " + userType);
            }
        } catch (PasswordIncorrectException e) {
            System.out.println("Password incorrect.");
        } catch (Exception e) {
            System.out.println("Invalid email or password. Please try again.");
            login(userType);
        }

        System.out.println("Enter [b] to go back, else any other key to try again.");
        String choice = CustScanner.getStrChoice();
        if (choice.equalsIgnoreCase("b")) {
            WelcomeDisplay.welcome();
        } else {
            System.out.println("Please try again.");
            login(userType);
        }
    }
}
